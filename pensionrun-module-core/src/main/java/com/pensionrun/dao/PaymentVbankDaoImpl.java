package com.pensionrun.dao;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.PaymentVbank;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Payment;
import com.pensionrun.entity.RoomPrice;
@Repository
public class PaymentVbankDaoImpl  extends GenericDaoImpl<PaymentVbank> implements PaymentVbankDao{
		@PersistenceContext
	    private EntityManager em;


		
}
