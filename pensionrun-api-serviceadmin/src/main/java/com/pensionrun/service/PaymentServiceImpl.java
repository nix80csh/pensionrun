package com.pensionrun.service;

import java.text.ParseException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.OrderDao;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.entity.Order;
import com.pensionrun.util.WooriPensionUtil;
import com.pensionrun.vo.WooriPensionVo;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private OrderDao orderDao;
	

	@Override
	public JsonDto<Integer> readCancelRepay(int idfOrder, String date) {
		// TODO Auto-generated method stub
		JsonDto<Integer> jDto=new JsonDto<Integer>();
		WooriPensionVo wooriPensionVo = new WooriPensionVo();
		Order order=orderDao.readById(idfOrder);
		BeanUtils.copyProperties(order, wooriPensionVo);
		try {
			jDto.setDataObject(WooriPensionUtil.calcurateCancelCharege(wooriPensionVo, date));
			jDto.setResultCode("S");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			jDto.setResultCode("F ");
		}
		return jDto;
	}


	@Override
	public ResponseEntity<Object> cancelWooriPension(int idfOrder, String price) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResponseEntity<Object> cancelNicePay(int idfOrder, String price) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResponseEntity<Object> cancelOrder(int idfOrder, String state) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
