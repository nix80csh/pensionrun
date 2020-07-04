package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.Area1Dto;
import com.pensionrun.dto.Area2Dto;
import com.pensionrun.dto.FacilityDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.ProvideDto;
import com.pensionrun.dto.ThemeDto;

@Transactional
public interface PensionService {
	


	@Transactional(readOnly=true)
	JsonDto<Area1Dto> area1List();
	
	@Transactional(readOnly=true)
	JsonDto<Area2Dto> area2List();
	
	@Transactional(readOnly=true)
	JsonDto<FacilityDto> facilityList();
	
	@Transactional(readOnly=true)
	JsonDto<ProvideDto> provideList();
	
	@Transactional(readOnly=true)
	JsonDto<ThemeDto> themeList();
}
