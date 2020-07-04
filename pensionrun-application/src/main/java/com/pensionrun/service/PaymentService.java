package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.NicePayDto;
import com.pensionrun.dto.OrderDto;
import com.pensionrun.dto.PaymentCancelVbankDto;

@Transactional
public interface PaymentService {
	public boolean nicePayStart(Model model,OrderDto orderDto,String Type);
	public JsonDto<Object> nicePayCallback(NicePayDto nicePayDto);
	public void nicePayVbankCallback(NicePayDto nicePayDto);
	public JsonDto<Object> cancelOrder(int idfOrder);
	public JsonDto<Object> cancelPaymentCard(int idfOrder);
	public JsonDto<Object> cancelPaymentVbank(int idfOrder,PaymentCancelVbankDto paymentCancelVbankDto);
}
