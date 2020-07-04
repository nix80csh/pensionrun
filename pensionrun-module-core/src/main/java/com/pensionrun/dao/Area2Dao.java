package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.Area2;

public interface Area2Dao extends GenericDao<Area2> {
	List<Area2> readByName(String a2Name); 
}
