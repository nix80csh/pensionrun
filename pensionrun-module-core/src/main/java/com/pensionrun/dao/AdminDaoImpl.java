package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Admin;

@Repository
public class AdminDaoImpl extends GenericDaoImpl<Admin> implements AdminDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Admin readByEmail(String email) {
		Query q = em.createQuery("select a from Admin a where email= :email");
		q.setParameter("email", email);
		Admin admin = null;
		try{
			admin = (Admin) q.getSingleResult();			
		}catch(NoResultException e){			
			admin = null;
		}				
		return admin;
	}

}
