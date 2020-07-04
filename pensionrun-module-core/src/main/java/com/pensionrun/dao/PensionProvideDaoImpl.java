package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.PensionProvide;

@Repository
public class PensionProvideDaoImpl extends GenericDaoImpl<PensionProvide> implements PensionProvideDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Integer deleteByIdfPension(Integer idfPension) {
		Query q = em.createQuery("delete from PensionProvide pp where pp.id.idfPension = :idfPension");
		return q.setParameter("idfPension", idfPension).executeUpdate();
	}

	@Override
	public List<PensionProvide> readByIdfPension(Integer idfPension) {
		Query q = em.createQuery("from PensionProvide pp where pp.pension.idfPension = :idfPension", PensionProvide.class);
		q.setParameter("idfPension", idfPension).getResultList();		
		return q.getResultList();
	}
}
