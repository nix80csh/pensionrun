package com.pensionrun.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.OrderDao;
import com.pensionrun.dao.PensionDao;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.OrderDto;
import com.pensionrun.entity.Account;
import com.pensionrun.entity.Order;
import com.pensionrun.entity.Room;
import com.pensionrun.util.WooriPensionProvider;
import com.pensionrun.util.WooriPensionUtil;
import com.pensionrun.vo.OrderSearchVo;
import com.pensionrun.vo.WooriPensionAddPriceVo;
import com.pensionrun.vo.WooriPensionVo;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao orderDao;
	@Override
	public JsonDto<Order> orderUpdate(OrderDto orderDto) {
		// TODO Auto-generated method stub
		JsonDto<Order> jDto=new JsonDto<Order>();
		try{
			Order order=new Order();
			BeanUtils.copyProperties(orderDto,order);
			Account account=new Account();
			account.setIdfAccount(orderDto.getIdfAccount());
			order.setAccount(account);
			Room room=new Room();
			room.setIdfRoom(orderDto.getIdfRoom());
			order.setRoom(room);
			orderDao.update(order);
			jDto.setResultCode("S");
			jDto.setDataObject(order);
		}catch(Exception e){
			jDto.setResultCode("F");
		}
		return jDto;
	}
	@Override
	public JsonDto<Order> orderDelete(Integer idfOrder) {
		// TODO Auto-generated method stub
		JsonDto<Order> jDto=new JsonDto<Order>();
		try{
			Order order=orderDao.readById(idfOrder);
			orderDao.delete(order);
			jDto.setResultCode("S");
		}catch(Exception e){
			jDto.setResultCode("F");
		}
		return jDto;
	}
	@Override
	public JsonDto<WooriPensionAddPriceVo> readAddPrice(WooriPensionVo wooriPensionVo) {
		// TODO Auto-generated method stub
		JsonDto<WooriPensionAddPriceVo> jDto=new JsonDto<WooriPensionAddPriceVo>();
		try{
			List<WooriPensionAddPriceVo> list=WooriPensionUtil.readAddPriceList(wooriPensionVo);
			for(int i=0;i<list.size();i++){
				if(list.get(i).getIdfProvierRoom().equals(wooriPensionVo.getIdfProvierRoom())){
					jDto.setDataObject(list.get(i));
					jDto.setResultCode("S");
				}
			}
		}catch(Exception e){
			jDto.setResultCode("F");
		}
		return jDto;
	}
	@Override
	public JsonDto<Order> orderCreate(OrderDto orderDto) {
		// TODO Auto-generated method stub

		JsonDto<Order> jDto=new JsonDto<Order>();

		Order order=new Order();
		BeanUtils.copyProperties(orderDto, order);
		Account account=new Account();
		account.setIdfAccount(orderDto.getIdfAccount());
		order.setAccount(account);
		Room room=new Room();
		room.setIdfRoom(orderDto.getIdfRoom());
		order.setRoom(room);

		orderDao.create(order);
		if(orderDto.isReservation()){
			WooriPensionVo wooriPensionVo=new WooriPensionVo();
			int idfRoom = order.getRoom().getIdfRoom();
			String idfPensionProvider = WooriPensionProvider.pensionProvierByIdfRoom.get(idfRoom);
			BeanUtils.copyProperties(order, wooriPensionVo);
			wooriPensionVo.setIdfProvider(idfPensionProvider);
			wooriPensionVo.setIdfProvierRoom(
					WooriPensionProvider.roomProvider.get(idfPensionProvider).getKey(idfRoom).toString());
			String reservationCode=null;
			try {
				reservationCode=WooriPensionUtil.reservationHolding(wooriPensionVo);
				if(reservationCode == null||reservationCode.equals("")){
					throw new Exception();
				}
				wooriPensionVo.setReservationCode(reservationCode);
				if(!WooriPensionUtil.reservationConfirm(wooriPensionVo).equals("1")){
					throw new Exception();
				}
				order.setReservationCode(reservationCode);
				orderDao.update(order);
				jDto.setResultCode("S");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				jDto.setResultCode("F");
			}
		}else{
			jDto.setResultCode("S");
		}
		jDto.setDataObject(order);
		jDto.setResultCode("F");
		return jDto;
	}
	@Override
	public JsonDto<Object> readOrderListByOrderSearchVo(
			OrderSearchVo orderSearchVo) {
		// TODO Auto-generated method stub
		JsonDto<Object> jDto=new JsonDto<Object>();
		try{
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("maxResult",orderDao.getMaxSizeByOrderSearchVo(orderSearchVo));
			List<Order> orderList=orderDao.readByOrderSearchVo(orderSearchVo);
			List<OrderDto> orderDtoList=new ArrayList<OrderDto>();
			for(Order order:orderList){
				OrderDto orderDto=new OrderDto();
				BeanUtils.copyProperties(order, orderDto);
				orderDto.setIdfAccount(order.getAccount().getIdfAccount());
				orderDto.setIdfRoom(order.getRoom().getIdfRoom());
				orderDto.setRoomName(order.getRoom().getName());
				orderDto.setPensionName(order.getRoom().getPension().getName());
				orderDtoList.add(orderDto);
			}
			
			map.put("orderList",orderDtoList);
			jDto.setDataObject(map);
			jDto.setResultCode("S");
		}catch(Exception e){
			jDto.setResultCode("F");
		}
		return jDto;
	}


}
