package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.RoomImage;

@Repository
public class RoomImageDaoImpl extends GenericDaoImpl<RoomImage> implements RoomImageDao {
	@PersistenceContext
	private EntityManager em;	

	@Override
	public List<RoomImage> readRoomImageByIdfRoom(Integer idfRoom) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("from RoomImage ri where ri.room.idfRoom = :idfRoom", RoomImage.class);
		q.setParameter("idfRoom", idfRoom);
		return q.getResultList();
	}
}
