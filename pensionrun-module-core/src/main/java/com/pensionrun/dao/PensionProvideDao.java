package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.PensionProvide;

public interface PensionProvideDao extends GenericDao<PensionProvide> {
	Integer deleteByIdfPension(Integer idfPension);
	List<PensionProvide> readByIdfPension(Integer idfPension);
}
