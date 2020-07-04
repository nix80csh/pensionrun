package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionCountPerAreaListDto;
import com.pensionrun.dto.SearchEngineDto;

@Transactional
public interface SearchService {
	@Transactional(readOnly=true)
	JsonDto<PensionCountPerAreaListDto> pensionCountPerAreaList();
	JsonDto<SearchEngineDto> searchEngineList(String searchName);
}
