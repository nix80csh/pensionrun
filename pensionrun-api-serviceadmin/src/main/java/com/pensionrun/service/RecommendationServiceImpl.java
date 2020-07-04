package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.RecommendationPensionDao;
import com.pensionrun.dao.RecommendationDao;
import com.pensionrun.dto.AdvertisementDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionProvideDto;
import com.pensionrun.dto.RecommendationDto;
import com.pensionrun.dto.RecommendationPensionDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.entity.Advertisement;
import com.pensionrun.entity.PensionProvide;
import com.pensionrun.entity.Recommendation;
import com.pensionrun.entity.RecommendationPension;
import com.pensionrun.entity.RecommendationPensionId;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	@Autowired
	RecommendationDao recommendationDao;
	@Autowired
	RecommendationPensionDao pensionRecommendationDao;

	@Override
	public JsonDto<RecommendationDto> recommendationCreate(RecommendationDto _recommendationDto) {
		JsonDto<RecommendationDto> jDto = new JsonDto<RecommendationDto>();
		Recommendation recommendation = new Recommendation();

		BeanUtils.copyProperties(_recommendationDto, recommendation);
		recommendationDao.create(recommendation);
		_recommendationDto.setIdfRecommendation(recommendation.getIdfRecommendation());
		jDto.setResultCode("S");
		jDto.setDataObject(_recommendationDto);
		return jDto;
	}

	@Override
	public JsonDto<RecommendationDto> recommendationUpdate(RecommendationDto _recommendationDto) {
		JsonDto<RecommendationDto> jDto = new JsonDto<RecommendationDto>();
		Recommendation recommendation = new Recommendation();
		BeanUtils.copyProperties(_recommendationDto, recommendation);
		recommendationDao.update(recommendation);
		jDto.setResultCode("S");
		// jDto.setDataObject(_pensionDto);
		return jDto;
	}

	@Override
	public JsonDto<RecommendationDto> recommendationDelete(RecommendationDto _recommendationDto) {
		JsonDto<RecommendationDto> jDto = new JsonDto<RecommendationDto>();
		Recommendation recommendation = recommendationDao.readById(_recommendationDto.getIdfRecommendation());

		if (recommendation == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist Advertisement");
		} else {
			recommendationDao.delete(recommendation);
			jDto.setResultCode("S");
		}
		return jDto;
	}

	@Override
	public JsonDto<RecommendationDto> recommendationList() {
		JsonDto<RecommendationDto> jDto = new JsonDto<RecommendationDto>();
		List<RecommendationDto> recommendationDtoList = new ArrayList<RecommendationDto>();
		List<Recommendation> recommendationList = recommendationDao.readAll();

		for (Recommendation r : recommendationList) {
			RecommendationDto recommendationDto = new RecommendationDto();

			BeanUtils.copyProperties(r, recommendationDto);
			recommendationDtoList.add(recommendationDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(recommendationDtoList);
		return jDto;
	}

	@Override
	public JsonDto<RecommendationPensionDto> recommendationPensionCreate(WrapperDto _wrapperDto) {
		JsonDto<RecommendationPensionDto> jDto = new JsonDto<RecommendationPensionDto>();
		List<RecommendationPensionDto> recommendationPensionDtoList = _wrapperDto.getRecommendationPension();

		// 삭제
		pensionRecommendationDao.deleteByIdfRecommendation(recommendationPensionDtoList.get(0).getIdfRecommendation());

		// 등록
		for (RecommendationPensionDto rDto : recommendationPensionDtoList) {
			RecommendationPension recommendationPension = new RecommendationPension();
			RecommendationPensionId recommendationPensionId = new RecommendationPensionId();
			recommendationPensionId.setIdfPension(rDto.getIdfPension());
			recommendationPensionId.setIdfRecommendation(rDto.getIdfRecommendation());
			recommendationPension.setId(recommendationPensionId);
			pensionRecommendationDao.create(recommendationPension);
		}
		jDto.setResultCode("S");
		jDto.setDataList(recommendationPensionDtoList);

		return jDto;
	}

	@Override
	public JsonDto<RecommendationDto> advertisementPriorityUpdate(WrapperDto _wrapperDto) {
		JsonDto<RecommendationDto> jDto = new JsonDto<RecommendationDto>();
		List<RecommendationDto> recommendationDtoList = _wrapperDto.getRecommendation();

		for (RecommendationDto r : recommendationDtoList) {
			Recommendation recommendation = recommendationDao.readById(r.getIdfRecommendation());
			recommendation.setPriority(r.getPriority());
			recommendationDao.update(recommendation);
		}
		jDto.setResultCode("S");
		// jDto.setDataObject(_pensionDto);
		return jDto;
	}

	@Override
	public JsonDto<RecommendationPensionDto> readPensionProvideByIdfPension(Integer _idfRecommendation) {
		JsonDto<RecommendationPensionDto> jDto = new JsonDto<RecommendationPensionDto>();
		List<RecommendationPensionDto> RecommendationPensionDtoList = new ArrayList<RecommendationPensionDto>();
		List<RecommendationPension> recommendationPensionList = pensionRecommendationDao
				.readByIdfRecommendation(_idfRecommendation);
		if (recommendationPensionList.size() == 0) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist RecommendationPension");
		} else {
			for (RecommendationPension rp : recommendationPensionList) {
				RecommendationPensionDto recommendationPensionDto = new RecommendationPensionDto();
				recommendationPensionDto.setIdfPension(rp.getPension().getIdfPension());
				recommendationPensionDto.setIdfRecommendation(rp.getRecommendation().getIdfRecommendation());
				RecommendationPensionDtoList.add(recommendationPensionDto);
			}
			jDto.setResultCode("S");
			jDto.setDataList(RecommendationPensionDtoList);
		}
		return jDto;

	}

}
