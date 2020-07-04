package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.Area1Dto;
import com.pensionrun.dto.Area2Dto;
import com.pensionrun.dto.FacilityDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionDto;
import com.pensionrun.dto.PensionFacilityDto;
import com.pensionrun.dto.PensionProvideDto;
import com.pensionrun.dto.PensionThemeDto;
import com.pensionrun.dto.ProvideDto;
import com.pensionrun.dto.ThemeDto;
import com.pensionrun.dto.WrapperDto;

@Transactional
public interface PensionService {
	

	
	JsonDto<PensionDto> pensionCreate(PensionDto _pensionDto);	
	JsonDto<PensionDto> pensionUpdate(PensionDto _pensionDto);	
	JsonDto<PensionDto> pensionDelete(PensionDto _pensionDto);
	
	JsonDto<PensionFacilityDto> pensionFacilityCreate(WrapperDto _wrapperDto);	
	JsonDto<PensionProvideDto> pensionProvideCreate(WrapperDto _wrapperDto);		
	JsonDto<PensionThemeDto> pensionThemeCreate(WrapperDto _wrapperDto);	

	JsonDto<PensionDto> readPensionByIdfPension(Integer _idfPension);
	JsonDto<PensionFacilityDto> readPensionFacilityByIdfPension(Integer _idfPension);
	JsonDto<PensionThemeDto> readPensionThemeByIdfPension(Integer _idfPension);
	JsonDto<PensionProvideDto> readPensionProvideByIdfPension(Integer _idfPension);
	
	@Transactional(readOnly=true)
	JsonDto<PensionDto> pensionList();
	
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
