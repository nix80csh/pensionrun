package com.pensionrun.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.NicePayDto;
import com.pensionrun.dto.OrderDto;
import com.pensionrun.dto.PaymentCancelVbankDto;
import com.pensionrun.service.PaymentService;

@Controller
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@RequestMapping("test")
	public String test(){
		return "test";
	}
	@RequestMapping("/nicePayStart")
	public String nicePayStart(Model model, OrderDto orderDto,
			String paymentType) throws Exception {
		paymentService.nicePayStart(model, orderDto, paymentType);
		return "nicePayStart";
	}

	@ResponseBody
	@RequestMapping(value = "/nicePayCallback", method = RequestMethod.POST)
	public ResponseEntity<Object> nicePayCallback(NicePayDto nicePayDto) {
		System.out.println("return");
		System.out.println(nicePayDto.toString());
		return new ResponseEntity<Object>(
				"OK", HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping(value = "/nicePayDbCallback", method = RequestMethod.POST)
	public ResponseEntity<Object> nicePayDbCallback(NicePayDto nicePayDto) {
		System.out.println("db");
		System.out.println(nicePayDto.toString());
		System.out.println("한글");
		try {
			System.out.println(new String(nicePayDto.getResultMsg().getBytes("utf-8"),"euc-kr"));
			System.out.println(new String(nicePayDto.getResultMsg().getBytes("euc-kr"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paymentService.nicePayCallback(nicePayDto);
		return new ResponseEntity<Object>("OK",
				 HttpStatus.OK);
	}
	@RequestMapping("/nicePayVbankCallback")
	public void nicePayVbankCallback(NicePayDto nicePayDto) {
		System.out.println(nicePayDto.toString());
		paymentService.nicePayVbankCallback(nicePayDto);
	}

	@RequestMapping("/cancelPaymentCard/{idfOrder}")
	public ResponseEntity<Object> cancelPayment(
			@PathVariable("idfOrder") int idfOrder) {
		return new ResponseEntity<Object>(
				paymentService.cancelPaymentCard(idfOrder), HttpStatus.OK);
	}

	@RequestMapping("/cancelPaymentVbank/{idfOrder}")
	public ResponseEntity<Object> cancelPaymentVbank(
			@PathVariable("idfOrder") int idfOrder,
			PaymentCancelVbankDto paymentCancelVbankDto) {
		return new ResponseEntity<Object>(paymentService.cancelPaymentVbank(
				idfOrder, paymentCancelVbankDto), HttpStatus.OK);
	}

	@RequestMapping("/cancelOrder/{idfOrder}")
	public ResponseEntity<Object> cancelOrder(
			@PathVariable("idfOrder") int idfOrder) {
		return new ResponseEntity<Object>(paymentService.cancelOrder(idfOrder),
				HttpStatus.OK);
	}
}
