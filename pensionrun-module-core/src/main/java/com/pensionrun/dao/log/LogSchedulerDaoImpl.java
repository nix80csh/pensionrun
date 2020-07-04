package com.pensionrun.dao.log;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.dao.GenericDaoImpl;
import com.pensionrun.entity.log.LogScheduler;

@Repository
public class LogSchedulerDaoImpl extends GenericDaoImpl<LogScheduler> implements LogSchedulerDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Date readMaxUpdDate(char type) {
		Query q = em.createQuery("select max(ls.scheduledDate) from LogScheduler ls where ls.type=:type",
				Date.class);
		q.setParameter("type", type);
		return (Date) q.getSingleResult();
	}

}
