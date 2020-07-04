package com.pensionrun.dao;

import com.pensionrun.entity.Payment;

public interface PaymentDao extends GenericDao<Payment>{
	public Payment readByIdfOrder(Integer idfOrder);
	
}
