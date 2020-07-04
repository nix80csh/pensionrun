package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Recommendation;

@Repository
public class RecommendationDaoImpl extends GenericDaoImpl<Recommendation> implements RecommendationDao {

	@PersistenceContext
	private EntityManager em;

}