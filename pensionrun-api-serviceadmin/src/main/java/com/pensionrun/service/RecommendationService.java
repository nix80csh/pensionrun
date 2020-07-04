package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionProvideDto;
import com.pensionrun.dto.RecommendationDto;
import com.pensionrun.dto.RecommendationPensionDto;
import com.pensionrun.dto.WrapperDto;
@Transactional
public interface RecommendationService {
	JsonDto<RecommendationDto> recommendationCreate(RecommendationDto _recommendationDto);
	JsonDto<RecommendationDto> recommendationUpdate(RecommendationDto _recommendationDto);
	JsonDto<RecommendationDto> recommendationDelete(RecommendationDto _recommendationDto);
	
	JsonDto<RecommendationDto> advertisementPriorityUpdate(WrapperDto _wrapperDto);
	
	JsonDto<RecommendationPensionDto> recommendationPensionCreate(WrapperDto _wrapperDto);	
	
	JsonDto<RecommendationPensionDto> readPensionProvideByIdfPension(Integer _idfRecommendation);
	
	@Transactional(readOnly=true)
	JsonDto<RecommendationDto> recommendationList();
}
