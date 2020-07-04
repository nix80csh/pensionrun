package com.pensionrun.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Payment;
import com.pensionrun.entity.RoomPrice;
@Repository
public class PaymentDaoImpl extends GenericDaoImpl<Payment> implements PaymentDao{
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Payment readByIdfOrder(Integer idfOrder) {
		// TODO Auto-generated method stub
			Payment payment=new Payment(); 
	        try {
	            String sql ="SELECT p FROM Payment p Where p.order.idfOrder=:idfOrder";
	            Query q = em.createQuery(sql, Payment.class);
	            q.setParameter("idfOrder", idfOrder);
	            payment = (Payment) q.getResultList().get(0);
	        } catch (IndexOutOfBoundsException e) {
	        	payment = null;
	        }
	        return payment;
	}

}
