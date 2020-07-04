package com.pensionrun.dao;

import java.util.Date;
import java.util.List;

import com.pensionrun.entity.RoomPrice;

public interface RoomPriceDao extends GenericDao<RoomPrice> {

	RoomPrice readTodayPriceByIdfPension(Integer idfPension);
	List<RoomPrice> readByCheckDate(Integer idfRoom, String startCheckDate, String endCheckDate);
	
}
