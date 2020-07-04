package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Event;

@Repository
public class EventDaoImpl extends GenericDaoImpl<Event> implements EventDao{
	
	@PersistenceContext
	private EntityManager em;
	


}
