package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pensionrun.service.PaymentService;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {
	@Autowired	
	private PaymentService paymentService;
	@RequestMapping("/readCancelRepay/{idfOrder}")
	public ResponseEntity<Object> readCancelRepay(@PathVariable("idfOrder")int idfOrder,@RequestBody String date) {
		return new ResponseEntity<Object>(paymentService.readCancelRepay(idfOrder,date), HttpStatus.OK);
	}
	@RequestMapping("/cancelWooriPension/{idfOrder}")
	public ResponseEntity<Object> cancelWooriPension(@PathVariable("idfOrder")int idfOrder,@RequestBody String price) {
		return new ResponseEntity<Object>(paymentService.readCancelRepay(idfOrder,price), HttpStatus.OK);
	}
	@RequestMapping("/cancelNicePay/{idfOrder}")
	public ResponseEntity<Object> cancelNicePay(@PathVariable("idfOrder")int idfOrder,@RequestBody String price) {
		return new ResponseEntity<Object>(paymentService.readCancelRepay(idfOrder,price), HttpStatus.OK);
	}
	@RequestMapping("/cancelOrder/{idfOrder}")
	public ResponseEntity<Object> cancelOrder(@PathVariable("idfOrder")int idfOrder,String state) {
		return new ResponseEntity<Object>(paymentService.readCancelRepay(idfOrder,state), HttpStatus.OK);
	}

}
