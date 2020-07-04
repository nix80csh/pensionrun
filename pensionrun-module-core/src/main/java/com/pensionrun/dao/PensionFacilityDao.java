package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.PensionFacility;

public interface PensionFacilityDao extends GenericDao<PensionFacility> {
//	PensionFacility readById(PensionFacilityId pensionFacilityId);
	
	Integer deleteByIdfPension(Integer idfPension);
	List<PensionFacility> readByIdfPension(Integer idfPension);
}
