package com.pensionrun.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.pensionrun.dto.JsonDto;

@Transactional
public interface PaymentService {
	public JsonDto<Integer> readCancelRepay(int idfOrder,String date);	
	public ResponseEntity<Object> cancelWooriPension(int idfOrder,@RequestBody String price);
	public ResponseEntity<Object> cancelNicePay(int idfOrder,@RequestBody String price);
	public ResponseEntity<Object> cancelOrder(int idfOrder,String state);
}
