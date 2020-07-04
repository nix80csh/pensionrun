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
import com.pensionrun.dto.PensionDto;
import com.pensionrun.dto.PensionFacilityDto;
import com.pensionrun.dto.PensionProvideDto;
import com.pensionrun.dto.PensionThemeDto;
import com.pensionrun.dto.ProvideDto;
import com.pensionrun.dto.ThemeDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.entity.Area1;
import com.pensionrun.entity.Area2;
import com.pensionrun.entity.Facility;
import com.pensionrun.entity.Pension;
import com.pensionrun.entity.PensionFacility;
import com.pensionrun.entity.PensionFacilityId;
import com.pensionrun.entity.PensionProvide;
import com.pensionrun.entity.PensionProvideId;
import com.pensionrun.entity.PensionTheme;
import com.pensionrun.entity.PensionThemeId;
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
	public JsonDto<PensionDto> pensionCreate(PensionDto _pensionDto) {
		JsonDto<PensionDto> jDto = new JsonDto<PensionDto>();
		Pension pension = new Pension();
		Area2 area2 = new Area2();

		area2.setIdfArea2(_pensionDto.getIdfArea2());
		pension.setArea2(area2);
		BeanUtils.copyProperties(_pensionDto, pension);
		pensionDao.create(pension);
		_pensionDto.setIdfPension(pension.getIdfPension());

		jDto.setResultCode("S");
		jDto.setDataObject(_pensionDto);
		return jDto;
	}

	public JsonDto<PensionDto> pensionUpdate(PensionDto _pensionDto) {
		JsonDto<PensionDto> jDto = new JsonDto<PensionDto>();
		Pension pension = new Pension();
		Area2 area2 = new Area2();

		area2.setIdfArea2(_pensionDto.getIdfArea2());
		pension.setArea2(area2);
		BeanUtils.copyProperties(_pensionDto, pension);
		pensionDao.update(pension);

		jDto.setResultCode("S");
		// jDto.setDataObject(_pensionDto);
		return jDto;

	}

	@Override
	public JsonDto<PensionDto> pensionDelete(PensionDto _pensionDto) {
		JsonDto<PensionDto> jDto = new JsonDto<PensionDto>();
		Pension pension = pensionDao.readById(_pensionDto.getIdfPension());

		if (pension == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist Pension");
		} else {
			pensionDao.delete(pension);
			jDto.setResultCode("S");
		}
		return jDto;
	}

	@Override
	public JsonDto<PensionFacilityDto> pensionFacilityCreate(WrapperDto _wrapperDto) {

		JsonDto<PensionFacilityDto> jDto = new JsonDto<PensionFacilityDto>();
		List<PensionFacilityDto> pensionFacilityDtoList = _wrapperDto.getPensionFacility();

		// 삭제
		pensionFacilityDao.deleteByIdfPension(pensionFacilityDtoList.get(0).getIdfPension());

		// 등록
		for (PensionFacilityDto pDto : pensionFacilityDtoList) {
			PensionFacility pensionFacility = new PensionFacility();
			PensionFacilityId pensionFacilityId = new PensionFacilityId();
			pensionFacilityId.setIdfFacility(pDto.getIdfFacility());
			pensionFacilityId.setIdfPension(pDto.getIdfPension());
			pensionFacility.setId(pensionFacilityId);
			pensionFacility.setPrice(pDto.getPrice());
			pensionFacility.setDescription(pDto.getDescription());
			pensionFacilityDao.create(pensionFacility);
		}

		jDto.setResultCode("S");
		jDto.setDataList(pensionFacilityDtoList);

		return jDto;
	}

	@Override
	public JsonDto<PensionProvideDto> pensionProvideCreate(WrapperDto _wrapperDto) {
		JsonDto<PensionProvideDto> jDto = new JsonDto<PensionProvideDto>();
		List<PensionProvideDto> pensionProvideDtoList = _wrapperDto.getPensionProvide();

		// 삭제
		pensionProvideDao.deleteByIdfPension(pensionProvideDtoList.get(0).getIdfPension());

		// 등록
		for (PensionProvideDto pDto : pensionProvideDtoList) {
			PensionProvide pensionProvide = new PensionProvide();
			PensionProvideId pensionProvideId = new PensionProvideId();

			pensionProvideId.setIdfPension(pDto.getIdfPension());
			pensionProvideId.setIdfProvide(pDto.getIdfProvide());
			pensionProvide.setId(pensionProvideId);
			pensionProvide.setPrice(pDto.getPrice());
			pensionProvide.setDescription(pDto.getDescription());
			pensionProvideDao.create(pensionProvide);
		}

		jDto.setResultCode("S");
		jDto.setDataList(pensionProvideDtoList);

		return jDto;
	}

	@Override
	public JsonDto<PensionThemeDto> pensionThemeCreate(WrapperDto _wrapperDto) {
		JsonDto<PensionThemeDto> jDto = new JsonDto<PensionThemeDto>();
		List<PensionThemeDto> pensionThemeDtoList = _wrapperDto.getPensionTheme();

		// 삭제
		pensionThemeDao.deleteByIdfPension(pensionThemeDtoList.get(0).getIdfPension());

		// 등록
		for (PensionThemeDto pDto : pensionThemeDtoList) {
			PensionTheme pensionTheme = new PensionTheme();
			PensionThemeId pensionThemeId = new PensionThemeId();

			pensionThemeId.setIdfPension(pDto.getIdfPension());
			pensionThemeId.setIdfTheme(pDto.getIdfTheme());

			pensionTheme.setId(pensionThemeId);
			pensionThemeDao.create(pensionTheme);
		}
		jDto.setResultCode("S");
		jDto.setDataList(pensionThemeDtoList);

		return jDto;
	}

	@Override
	public JsonDto<PensionDto> pensionList() {
		JsonDto<PensionDto> jDto = new JsonDto<PensionDto>();
		List<PensionDto> pensionDtoList = new ArrayList<PensionDto>();
		List<Pension> pensionList = pensionDao.readAll();

		for (Pension p : pensionList) {
			PensionDto pensionDto = new PensionDto();
			pensionDto.setIdfArea2(p.getArea2().getIdfArea2());
			BeanUtils.copyProperties(p, pensionDto);
			pensionDtoList.add(pensionDto);
		}

		jDto.setResultCode("S");
		jDto.setDataList(pensionDtoList);
		return jDto;
	}

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

	@Override
	public JsonDto<PensionDto> readPensionByIdfPension(Integer _idfPension) {
		JsonDto<PensionDto> jDto = new JsonDto<PensionDto>();
		PensionDto pensionDto = new PensionDto();
		Pension pension = pensionDao.readById(_idfPension);
		if (pension == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist Pension");
		} else {
			pensionDto.setIdfArea2(pension.getArea2().getIdfArea2());
			BeanUtils.copyProperties(pension, pensionDto);
			jDto.setResultCode("S");
			jDto.setDataObject(pensionDto);
		}
		return jDto;
	}

	public JsonDto<PensionFacilityDto> readPensionFacilityByIdfPension(Integer _idfPension) {
		JsonDto<PensionFacilityDto> jDto = new JsonDto<PensionFacilityDto>();		
		List<PensionFacilityDto> pensionFacilityDtoList = new ArrayList<PensionFacilityDto>();
		List<PensionFacility> pensionFacilityList = pensionFacilityDao.readByIdfPension(_idfPension);
		
		if (pensionFacilityList.size() == 0) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist PensionFacility");
		} else {
			for(PensionFacility pf : pensionFacilityList){
				PensionFacilityDto pensionFacilityDto = new PensionFacilityDto();
				pensionFacilityDto.setIdfPension(pf.getId().getIdfPension());
				pensionFacilityDto.setIdfFacility(pf.getId().getIdfFacility());
				pensionFacilityDto.setName(pf.getFacility().getName());
				BeanUtils.copyProperties(pf, pensionFacilityDto);
				pensionFacilityDtoList.add(pensionFacilityDto);
			}
			jDto.setResultCode("S");
			jDto.setDataList(pensionFacilityDtoList);
		}
		return jDto;
	}

	
	@Override
	public JsonDto<PensionThemeDto> readPensionThemeByIdfPension(Integer _idfPension) {
		JsonDto<PensionThemeDto> jDto = new JsonDto<PensionThemeDto>();		
		List<PensionThemeDto> pensionThemeDtoList = new ArrayList<PensionThemeDto>();
		List<PensionTheme> pensionThemeList = pensionThemeDao.readByIdfPension(_idfPension);
		
		if (pensionThemeList.size() == 0) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist PensionTheme");
		} else {
			for(PensionTheme pt : pensionThemeList){
				PensionThemeDto pensionThemeDto = new PensionThemeDto();
				pensionThemeDto.setIdfPension(pt.getId().getIdfPension());
				pensionThemeDto.setIdfTheme(pt.getId().getIdfTheme());
				pensionThemeDto.setName(pt.getTheme().getName());
				BeanUtils.copyProperties(pt, pensionThemeDto);
				pensionThemeDtoList.add(pensionThemeDto);
			}
			jDto.setResultCode("S");
			jDto.setDataList(pensionThemeDtoList);
		}
		return jDto;
	}

	@Override
	public JsonDto<PensionProvideDto> readPensionProvideByIdfPension(Integer _idfPension) {
		JsonDto<PensionProvideDto> jDto = new JsonDto<PensionProvideDto>();		
		List<PensionProvideDto> pensionProvideDtoList = new ArrayList<PensionProvideDto>();
		List<PensionProvide> pensionProvideList = pensionProvideDao.readByIdfPension(_idfPension);
		
		if (pensionProvideList.size() == 0) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist PensionProvide");
		} else {
			for(PensionProvide pp : pensionProvideList){
				PensionProvideDto pensionProvideDto = new PensionProvideDto();
				pensionProvideDto.setIdfPension(pp.getId().getIdfPension());
				pensionProvideDto.setIdfProvide(pp.getId().getIdfProvide());
				pensionProvideDto.setName(pp.getProvide().getName());				
				BeanUtils.copyProperties(pp, pensionProvideDto);
				pensionProvideDtoList.add(pensionProvideDto);
			}
			jDto.setResultCode("S");
			jDto.setDataList(pensionProvideDtoList);
		}
		return jDto;
	}

	
}
