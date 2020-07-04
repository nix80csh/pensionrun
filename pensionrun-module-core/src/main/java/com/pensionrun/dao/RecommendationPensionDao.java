package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.RecommendationPension;

public interface RecommendationPensionDao extends GenericDao<RecommendationPension> {
	Integer deleteByIdfRecommendation(Integer idfRecommendation);
	List<RecommendationPension> readByIdfRecommendation(Integer idfRecommendation);	
	Long countByIdfRecommendation(Integer idfRecommendation);
}
