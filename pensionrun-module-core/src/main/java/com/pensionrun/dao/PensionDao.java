package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.Pension;

public interface PensionDao extends GenericDao<Pension> {
	List<Integer> readIdfArea2ListGroupByIdfArea2();	
	Long readPensionCount(Integer idfArea2);
	Long checkTodayNewPension(Integer idfArea2);
	List<Pension> readByName(String name);
}
