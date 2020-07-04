package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Advertisement;

@Repository
public class AdvertisementDaoImpl extends GenericDaoImpl<Advertisement> implements AdvertisementDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Advertisement> readAllByType(char adType) {
		Query q = em.createQuery("from Advertisement ad where ad.type = :adType and ad.state = 'Y'", Advertisement.class);		
		return q.setParameter("adType", adType).getResultList();
	}
	

}
