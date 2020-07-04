package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.Room;

public interface RoomDao extends GenericDao<Room> {
	
	
	List<Room> readByIdfPension(Integer idfPension);
	Long countByIdfPension(Integer idfPension);
	Room readByIdfProvider(String idfProvider, String idfProviderRoom);
}