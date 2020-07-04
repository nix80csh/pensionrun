package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.Area2Dao;
import com.pensionrun.dao.PensionDao;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionCountPerAreaListDto;
import com.pensionrun.dto.SearchEngineArea2Dto;
import com.pensionrun.dto.SearchEngineDto;
import com.pensionrun.dto.SearchEnginePensionDto;
import com.pensionrun.entity.Area2;
import com.pensionrun.entity.Pension;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	PensionDao pensionDao;
	@Autowired
	Area2Dao area2Dao;

	@Override
	public JsonDto<PensionCountPerAreaListDto> pensionCountPerAreaList() {
		JsonDto<PensionCountPerAreaListDto> jDto = new JsonDto<PensionCountPerAreaListDto>();
		List<PensionCountPerAreaListDto> pensionCountPerAreaListDtoList = new ArrayList<PensionCountPerAreaListDto>();
		List<Integer> idfArea2List = pensionDao.readIdfArea2ListGroupByIdfArea2();
		for (int idfArea2 : idfArea2List) {
			PensionCountPerAreaListDto pensionCountPerAreaListDto = new PensionCountPerAreaListDto();
			pensionCountPerAreaListDto.setIdfArea2(idfArea2);
			Long pensionCount = pensionDao.readPensionCount(idfArea2);
			pensionCountPerAreaListDto.setCount(pensionCount);
			Area2 area2 = area2Dao.readById(idfArea2);
			pensionCountPerAreaListDto.setA2Name(area2.getName());
			pensionCountPerAreaListDto.setIdfArea1(area2.getArea1().getIdfArea1());
			pensionCountPerAreaListDto.setA1Name(area2.getArea1().getName());
			Long checkTodayNewPensionCount = pensionDao.checkTodayNewPension(idfArea2);
			pensionCountPerAreaListDto.setCheckTodayNewPensionCount(checkTodayNewPensionCount);
			pensionCountPerAreaListDtoList.add(pensionCountPerAreaListDto);
		}
		jDto.setDataList(pensionCountPerAreaListDtoList);
		return jDto;
	}

	@Override
	public JsonDto<SearchEngineDto> searchEngineList(String searchName) {
		JsonDto<SearchEngineDto> jDto = new JsonDto<SearchEngineDto>();		
		List<Pension> pensionList = pensionDao.readByName(searchName);
		List<Area2> area2List = area2Dao.readByName(searchName);
		SearchEngineDto searchEngineDto = new SearchEngineDto();
		List<SearchEngineArea2Dto> searchEngineArea2DtoList = new ArrayList<SearchEngineArea2Dto>();
		List<SearchEnginePensionDto> searchEnginePensionDtoList = new ArrayList<SearchEnginePensionDto>();
		
		for(Area2 a : area2List){
			SearchEngineArea2Dto searchEngineArea2Dto = new SearchEngineArea2Dto();
			searchEngineArea2Dto.setIdfArea2(a.getIdfArea2());
			searchEngineArea2Dto.setName(a.getName());
			searchEngineArea2DtoList.add(searchEngineArea2Dto);
		}		
		
		for(Pension p : pensionList){
			SearchEnginePensionDto searchEnginePensionDto = new SearchEnginePensionDto();
			searchEnginePensionDto.setIdfPension(p.getIdfPension());
			searchEnginePensionDto.setName(p.getName());
			searchEnginePensionDtoList.add(searchEnginePensionDto);		
		}
		searchEngineDto.setArea2(searchEngineArea2DtoList);
		searchEngineDto.setPension(searchEnginePensionDtoList);
		jDto.setResultCode("S");
		jDto.setDataObject(searchEngineDto);
		return jDto;
	}

}
