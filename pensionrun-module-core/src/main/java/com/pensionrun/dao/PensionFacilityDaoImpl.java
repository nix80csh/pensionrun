package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.PensionFacility;

@Repository
public class PensionFacilityDaoImpl extends GenericDaoImpl<PensionFacility> implements PensionFacilityDao{
	@PersistenceContext
	private EntityManager em;

//	@Override
//	public PensionFacility readById(PensionFacilityId pensionFacilityId) {		
//		return em.find(PensionFacility.class, pensionFacilityId);
//	}

	@Override
	public Integer deleteByIdfPension(Integer idfPension) {		
		Query q = em.createQuery("delete from PensionFacility pf where pf.id.idfPension = :idfPension");
		return q.setParameter("idfPension", idfPension).executeUpdate();		
	}

	@Override
	public List<PensionFacility> readByIdfPension(Integer idfPension) {
		Query q = em.createQuery("from PensionFacility pf where pf.pension.idfPension = :idfPension", PensionFacility.class);
		q.setParameter("idfPension", idfPension).getResultList();		
		return q.getResultList();
	}
	

}
