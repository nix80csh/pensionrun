package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Area1;
import com.pensionrun.entity.Area2;


@Repository
public class Area2DaoImpl extends GenericDaoImpl<Area2> implements Area2Dao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Area2> readByName(String a2Name) {
		Query q = em.createQuery("from Area2 a where a.name like :name", Area2.class);
		q.setParameter("name","%"+a2Name+"%");
		return q.getResultList();
	}


}
