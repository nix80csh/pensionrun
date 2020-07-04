package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.RoomAmenity;

@Repository
public class RoomAmenityDaoImpl extends GenericDaoImpl<RoomAmenity> implements RoomAmenityDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Integer deleteByIdfRoom(Integer idfRoom) {		
		Query q = em.createQuery("delete from RoomAmenity ra where ra.id.idfRoom = :idfRoom");
		return q.setParameter("idfRoom", idfRoom).executeUpdate();		
	}

	@Override
	public List<RoomAmenity> readByIdfRoom(Integer idfRoom) {
		Query q = em.createQuery("from RoomAmenity ra where ra.room.idfRoom = :idfRoom", RoomAmenity.class);
		q.setParameter("idfRoom", idfRoom);
		List<RoomAmenity> roomAmenityList = null;
		
		try{
			roomAmenityList = q.getResultList();
		}catch(NoResultException e){
			roomAmenityList = null;
		}
		return 	roomAmenityList;	
	}
}
