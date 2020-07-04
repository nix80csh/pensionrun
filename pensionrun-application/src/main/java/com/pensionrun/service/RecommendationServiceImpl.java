package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.PensionDao;
import com.pensionrun.dao.RecommendationDao;
import com.pensionrun.dao.RecommendationPensionDao;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RecommendationDto;
import com.pensionrun.entity.Recommendation;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	RecommendationDao recommendationDao;
	@Autowired
	RecommendationPensionDao recommendationPensionDao;
	@Autowired
	PensionDao pensionDao;
	
	@Override
	public JsonDto<RecommendationDto> recommendationList() {
		JsonDto<RecommendationDto> jDto = new JsonDto<RecommendationDto>();
		List<RecommendationDto> recommendationDtoList = new ArrayList<RecommendationDto>();
		List<Recommendation> recommendationList = recommendationDao.readAll();

		for (Recommendation r : recommendationList) {
			RecommendationDto recommendationDto = new RecommendationDto();
			
			BeanUtils.copyProperties(r, recommendationDto);
			Long pensionCount = recommendationPensionDao.countByIdfRecommendation(r.getIdfRecommendation());
			recommendationDto.setPensionCount(pensionCount);
			recommendationDtoList.add(recommendationDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(recommendationDtoList);
		return jDto;
	}

}
