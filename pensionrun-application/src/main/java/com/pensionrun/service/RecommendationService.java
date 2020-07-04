package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RecommendationDto;

@Transactional
public interface RecommendationService {
	@Transactional(readOnly=true)
	JsonDto<RecommendationDto> recommendationList();
	
}
