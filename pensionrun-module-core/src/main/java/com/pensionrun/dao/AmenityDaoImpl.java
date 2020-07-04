package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Amenity;

@Repository
public class AmenityDaoImpl extends GenericDaoImpl<Amenity> implements AmenityDao {

	@PersistenceContext
	private EntityManager em;



}
