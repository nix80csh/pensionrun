package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.PensionTheme;

@Repository
public class PensionThemeDaoImpl extends GenericDaoImpl<PensionTheme> implements PensionThemeDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Integer deleteByIdfPension(Integer idfPension) {
		Query q = em.createQuery("delete from PensionTheme pt where pt.id.idfPension = :idfPension");
		return q.setParameter("idfPension", idfPension).executeUpdate();
	}

	@Override
	public List<PensionTheme> readByIdfPension(Integer idfPension) {
		Query q = em.createQuery("from PensionTheme pt where pt.pension.idfPension = :idfPension", PensionTheme.class);
		q.setParameter("idfPension", idfPension).getResultList();		
		return q.getResultList();
	}
}
