package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Provide;


@Repository
public class ProvideDaoImpl extends GenericDaoImpl<Provide> implements ProvideDao {

	@PersistenceContext
	private EntityManager em;
	


}
