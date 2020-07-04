package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Room;

@Repository
public class RoomDaoImpl extends GenericDaoImpl<Room> implements RoomDao  {

	@PersistenceContext
	private EntityManager em;	
	

	@Override
	public List<Room> readByIdfPension(Integer idfPension) {
		Query q = em.createQuery("from Room r where r.pension.idfPension = :idfPension", Room.class);
		q.setParameter("idfPension", idfPension);		
		return q.getResultList();
	}

	@Override
	public Long countByIdfPension(Integer idfPension) {
		Query q = em.createQuery("select count(r) from Room r where r.pension.idfPension = :idfPension", Long.class);
		q.setParameter("idfPension", idfPension);
		return (Long) q.getSingleResult();
	}

	@Override
	public Room readByIdfProvider(String idfProvider, String idfProviderRoom) {
		Query q = em.createQuery("from Room r where r.idfProvider = :idfProvider and r.idfProviderRoom=:idfProviderRoom", Room.class);
		q.setParameter("idfProvider", idfProvider);
		q.setParameter("idfProviderRoom", idfProviderRoom);
		return (Room) q.getSingleResult();
	}
}
