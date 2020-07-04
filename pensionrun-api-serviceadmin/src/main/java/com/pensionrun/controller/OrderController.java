package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pensionrun.dto.OrderDto;
import com.pensionrun.service.OrderService;
import com.pensionrun.vo.OrderSearchVo;
import com.pensionrun.vo.WooriPensionVo;
@Controller
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@RequestMapping("orderCreate")
	public ResponseEntity<Object> orderCreate(@RequestBody OrderDto orderDto){
		return new ResponseEntity<Object>(orderService.orderCreate(orderDto),HttpStatus.OK); 
	}
	@RequestMapping("orderUpdate")
	public ResponseEntity<Object> orderUpdate(@RequestBody OrderDto orderDto){
		return new ResponseEntity<Object>(orderService.orderUpdate(orderDto),HttpStatus.OK);
	}
	@RequestMapping("readAddPrice")
	public ResponseEntity<Object> readAddPriceList(@RequestBody WooriPensionVo wooriPensionVo){
		return new ResponseEntity<Object>(orderService.readAddPrice(wooriPensionVo),HttpStatus.OK);
	}
	@RequestMapping("reservationWooriPension")
	public ResponseEntity<Object> reservationWooriPension(@RequestBody WooriPensionVo wooriPensionVo){
		return new ResponseEntity<Object>(orderService.readAddPrice(wooriPensionVo),HttpStatus.OK);
	}
	@RequestMapping("readOrderListByOrderSearchVo")
	public ResponseEntity<Object> readOrderListByOrderSearchVo(@RequestBody OrderSearchVo orderSerachVo){
		return new ResponseEntity<Object>(orderService.readOrderListByOrderSearchVo(orderSerachVo),HttpStatus.OK);
	}
}
