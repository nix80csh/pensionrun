package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.RoomImage;

public interface RoomImageDao extends GenericDao<RoomImage> {	
	public List<RoomImage> readRoomImageByIdfRoom(Integer idfRoom); 
}
