package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.RecommendationPension;
@Repository
public class RecommendationPensionDaoImpl extends GenericDaoImpl<RecommendationPension> implements RecommendationPensionDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Integer deleteByIdfRecommendation(Integer idfRecommendation) {
		Query q = em.createQuery("delete from RecommendationPension pr where pr.id.idfRecommendation = :idfRecommendation");
		return q.setParameter("idfRecommendation", idfRecommendation).executeUpdate();
	}

	@Override
	public List<RecommendationPension> readByIdfRecommendation(Integer idfRecommendation) {
		Query q = em.createQuery("from RecommendationPension pr where pr.id.idfRecommendation = :idfRecommendation", RecommendationPension.class);
		q.setParameter("idfRecommendation", idfRecommendation).getResultList();		
		return q.getResultList();
	}

	@Override
	public Long countByIdfRecommendation(Integer idfRecommendation) {
		Query q = em.createQuery("select count(pr) from RecommendationPension pr where pr.id.idfRecommendation = :idfRecommendation", Long.class);
		q.setParameter("idfRecommendation", idfRecommendation);
		return (Long) q.getSingleResult();
	}

}
