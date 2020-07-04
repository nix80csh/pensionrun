package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.Area1Dao;
import com.pensionrun.dao.Area2Dao;
import com.pensionrun.dao.FacilityDao;
import com.pensionrun.dao.PensionDao;
import com.pensionrun.dao.PensionFacilityDao;
import com.pensionrun.dao.PensionProvideDao;
import com.pensionrun.dao.PensionThemeDao;
import com.pensionrun.dao.ProvideDao;
import com.pensionrun.dao.ThemeDao;
import com.pensionrun.dto.Area1Dto;
import com.pensionrun.dto.Area2Dto;
import com.pensionrun.dto.FacilityDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.ProvideDto;
import com.pensionrun.dto.ThemeDto;
import com.pensionrun.entity.Area1;
import com.pensionrun.entity.Area2;
import com.pensionrun.entity.Facility;
import com.pensionrun.entity.Provide;
import com.pensionrun.entity.Theme;

@Service
public class PensionServiceImpl implements PensionService {

	@Autowired
	private PensionDao pensionDao;
	@Autowired
	private PensionFacilityDao pensionFacilityDao;
	@Autowired
	private PensionProvideDao pensionProvideDao;
	@Autowired
	private PensionThemeDao pensionThemeDao;
	@Autowired
	private Area1Dao area1Dao;
	@Autowired
	private Area2Dao area2Dao;
	@Autowired
	private FacilityDao facilityDao;
	@Autowired
	private ProvideDao provideDao;
	@Autowired
	private ThemeDao themeDao;



	@Override
	public JsonDto<Area1Dto> area1List() {
		JsonDto<Area1Dto> jDto = new JsonDto<Area1Dto>();
		List<Area1Dto> area1DtoList = new ArrayList<Area1Dto>();
		List<Area1> area1List = area1Dao.readAll();

		for (Area1 a : area1List) {
			Area1Dto area1Dto = new Area1Dto();
			BeanUtils.copyProperties(a, area1Dto);
			area1DtoList.add(area1Dto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(area1DtoList);
		return jDto;
	}

	@Override
	public JsonDto<Area2Dto> area2List() {
		JsonDto<Area2Dto> jDto = new JsonDto<Area2Dto>();
		List<Area2Dto> area2DtoList = new ArrayList<Area2Dto>();
		List<Area2> area2List = area2Dao.readAll();

		for (Area2 a : area2List) {
			Area2Dto area2Dto = new Area2Dto();
			area2Dto.setIdfArea1(a.getArea1().getIdfArea1());
			BeanUtils.copyProperties(a, area2Dto);
			area2DtoList.add(area2Dto);

		}
		jDto.setResultCode("S");
		jDto.setDataList(area2DtoList);
		return jDto;
	}

	@Override
	public JsonDto<FacilityDto> facilityList() {

		JsonDto<FacilityDto> jDto = new JsonDto<FacilityDto>();
		List<FacilityDto> facilityDtoList = new ArrayList<FacilityDto>();
		List<Facility> facilityList = facilityDao.readAll();

		for (Facility f : facilityList) {
			FacilityDto facilityDto = new FacilityDto();

			BeanUtils.copyProperties(f, facilityDto);
			facilityDtoList.add(facilityDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(facilityDtoList);

		return jDto;
	}

	public JsonDto<ProvideDto> provideList() {
		JsonDto<ProvideDto> jDto = new JsonDto<ProvideDto>();
		List<ProvideDto> provideDtoList = new ArrayList<ProvideDto>();
		List<Provide> provideList = provideDao.readAll();

		for (Provide p : provideList) {
			ProvideDto provideDto = new ProvideDto();
			BeanUtils.copyProperties(p, provideDto);
			provideDtoList.add(provideDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(provideDtoList);

		return jDto;
	}

	@Override
	public JsonDto<ThemeDto> themeList() {
		JsonDto<ThemeDto> jDto = new JsonDto<ThemeDto>();
		List<ThemeDto> themeDtoList = new ArrayList<ThemeDto>();
		List<Theme> themeList = themeDao.readAll();
		for (Theme t : themeList) {
			ThemeDto themeDto = new ThemeDto();
			BeanUtils.copyProperties(t, themeDto);
			themeDtoList.add(themeDto);

		}

		jDto.setResultCode("S");
		jDto.setDataList(themeDtoList);

		return jDto;

	}


}
