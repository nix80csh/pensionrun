package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.Advertisement;

public interface AdvertisementDao extends GenericDao<Advertisement>{	
	List<Advertisement> readAllByType(char adType);
}
