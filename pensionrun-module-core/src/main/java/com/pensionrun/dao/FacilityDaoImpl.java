package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Facility;

@Repository
public class FacilityDaoImpl extends GenericDaoImpl<Facility> implements FacilityDao {

	@PersistenceContext
	private EntityManager em;


}
