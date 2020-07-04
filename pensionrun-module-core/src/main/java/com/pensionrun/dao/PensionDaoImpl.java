package com.pensionrun.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Area2;
import com.pensionrun.entity.Pension;

@Repository
public class PensionDaoImpl extends GenericDaoImpl<Pension> implements PensionDao {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Integer> readIdfArea2ListGroupByIdfArea2() {
		Query q = em.createQuery("select p.area2.idfArea2 as idfArea2 from Pension p Group By p.area2.idfArea2");				
		return q.getResultList();
	}

	@Override
	public Long readPensionCount(Integer idfArea2) {
		Query q = em.createQuery("select count(p.idfPension) from Pension p where p.area2.idfArea2=:idfArea2", Long.class);
		q.setParameter("idfArea2", idfArea2);
		return (Long) q.getSingleResult();
	}

	@Override
	public Long checkTodayNewPension(Integer idfArea2) {
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = df.format(new Date());		
		Query q = em.createQuery("select count(p.idfPension) from Pension p where p.area2.idfArea2=:idfArea2 and DATE_FORMAT(regDate,'%Y-%m-%d') = :todayDate", Long.class);
		q.setParameter("idfArea2", idfArea2);
		q.setParameter("todayDate", todayDate);
		return (Long) q.getSingleResult();
	}

	@Override
	public List<Pension> readByName(String name) {
		Query q = em.createQuery("from Pension p where p.name like :name", Pension.class);
		q.setParameter("name","%"+name+"%");
		return q.getResultList();
	}
	
	

}
