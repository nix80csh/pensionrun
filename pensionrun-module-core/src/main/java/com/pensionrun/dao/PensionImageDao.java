package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.PensionImage;

public interface PensionImageDao extends GenericDao<PensionImage> {
	List<PensionImage> readByIdfPension(Integer idfPension);
	PensionImage readMainImageByIdfPension(Integer idfPension);
}
