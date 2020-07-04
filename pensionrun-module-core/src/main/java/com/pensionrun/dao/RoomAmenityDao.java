package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.RoomAmenity;

public interface RoomAmenityDao extends GenericDao<RoomAmenity>{

	Integer deleteByIdfRoom(Integer idfRoom);
	List<RoomAmenity> readByIdfRoom(Integer idfRoom);
}
