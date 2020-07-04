package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.OrderDto;
import com.pensionrun.entity.Order;
import com.pensionrun.vo.OrderSearchVo;
import com.pensionrun.vo.WooriPensionAddPriceVo;
import com.pensionrun.vo.WooriPensionVo;

@Transactional
public interface OrderService {
	public JsonDto<Order> orderCreate(OrderDto orderDto);
	public JsonDto<Order> orderUpdate(OrderDto orderDto);
	public JsonDto<Order> orderDelete(Integer idfOrder);
	public JsonDto<WooriPensionAddPriceVo> readAddPrice(WooriPensionVo wooriPensionVo);
	public JsonDto<Object> readOrderListByOrderSearchVo(OrderSearchVo orderSearchVo);
}
