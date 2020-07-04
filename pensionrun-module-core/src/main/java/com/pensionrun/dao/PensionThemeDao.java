package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.PensionTheme;

public interface PensionThemeDao extends GenericDao<PensionTheme>{
	Integer deleteByIdfPension(Integer idfPension);
	
	List<PensionTheme> readByIdfPension(Integer idfPension);
	
}
