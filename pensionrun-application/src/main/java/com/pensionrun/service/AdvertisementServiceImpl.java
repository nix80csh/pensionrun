package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.AdvertisementDao;
import com.pensionrun.dto.AdvertisementDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.entity.Advertisement;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{

	@Autowired
	AdvertisementDao advertisementDao;
	
	@Override
	public JsonDto<AdvertisementDto> advertisementList(char _type) {
		JsonDto<AdvertisementDto> jDto = new JsonDto<AdvertisementDto>();
		List<AdvertisementDto> advertisementDtoList = new ArrayList<AdvertisementDto>();
		
		List<Advertisement> advertisementList = advertisementDao.readAllByType(_type);
		for (Advertisement a : advertisementList) {
			AdvertisementDto advertisementDto = new AdvertisementDto();			
			BeanUtils.copyProperties(a, advertisementDto);
			advertisementDto.setIdfPension(a.getPension().getIdfPension());
			advertisementDto.setPensionName(a.getPension().getName());
			advertisementDtoList.add(advertisementDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(advertisementDtoList);

		return jDto;
	}

}
