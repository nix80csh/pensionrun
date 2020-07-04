package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Review;

@Repository
public class ReviewDaoImpl extends GenericDaoImpl<Review> implements ReviewDao {
	@PersistenceContext
	private EntityManager em;

}
