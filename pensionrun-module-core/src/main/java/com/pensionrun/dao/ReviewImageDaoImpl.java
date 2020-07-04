package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.ReviewImage;

@Repository
public class ReviewImageDaoImpl extends GenericDaoImpl<ReviewImage> implements ReviewImageDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ReviewImage> readByIdfReview(Integer idfReview) {
		Query q = em.createQuery("from ReviewImage ri where ri.review.idfReview = :idfReview", ReviewImage.class);
		q.setParameter("idfReview", idfReview).getResultList();		
		return q.getResultList();
	}

}
