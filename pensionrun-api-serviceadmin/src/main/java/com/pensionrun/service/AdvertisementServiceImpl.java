package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.AdvertisementDao;
import com.pensionrun.dto.AdvertisementDto;
import com.pensionrun.dto.Area1Dto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RoomDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.entity.Advertisement;
import com.pensionrun.entity.Area1;
import com.pensionrun.entity.Pension;
import com.pensionrun.entity.Room;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired 
	AdvertisementDao advertisementDao;
	
	@Override
	public JsonDto<AdvertisementDto> advertisementCreate(AdvertisementDto _advertisementDto) {
		JsonDto<AdvertisementDto> jDto = new JsonDto<AdvertisementDto>();
		Advertisement advertisement = new Advertisement();
		Pension pension = new Pension();

		pension.setIdfPension(_advertisementDto.getIdfPension());
		advertisement.setPension(pension);
		BeanUtils.copyProperties(_advertisementDto, advertisement);
		advertisementDao.create(advertisement);
		_advertisementDto.setIdfAdvertisement(advertisement.getIdfAdvertisement());

		jDto.setResultCode("S");
		jDto.setDataObject(_advertisementDto);
		return jDto;
	}

	@Override
	public JsonDto<AdvertisementDto> advertisementUpdate(AdvertisementDto _advertisementDto) {
		JsonDto<AdvertisementDto> jDto = new JsonDto<AdvertisementDto>();
		Advertisement advertisement = new Advertisement();		
		Pension pension = new Pension();
		pension.setIdfPension(_advertisementDto.getIdfPension());
		advertisement.setPension(pension);
		BeanUtils.copyProperties(_advertisementDto, advertisement);
		advertisementDao.update(advertisement);
		jDto.setResultCode("S");
		// jDto.setDataObject(_pensionDto);
		return jDto;
	}

	@Override
	public JsonDto<AdvertisementDto> advertisementDelete(AdvertisementDto _advertisementDto) {
		JsonDto<AdvertisementDto> jDto = new JsonDto<AdvertisementDto>();
		Advertisement advertisement = advertisementDao.readById(_advertisementDto.getIdfAdvertisement());

		if (advertisement == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist Advertisement");
		} else {
			advertisementDao.delete(advertisement);
			jDto.setResultCode("S");
		}
		return jDto;
	}

	@Override
	public JsonDto<AdvertisementDto> advertisementList() {
		JsonDto<AdvertisementDto> jDto = new JsonDto<AdvertisementDto>();
		List<AdvertisementDto> advertisementDtoList = new ArrayList<AdvertisementDto>();
		List<Advertisement> advertisementList = advertisementDao.readAll();

		for (Advertisement a : advertisementList) {
			AdvertisementDto advertisementDto = new AdvertisementDto();
			advertisementDto.setIdfPension(a.getPension().getIdfPension());
			BeanUtils.copyProperties(a, advertisementDto);
			advertisementDtoList.add(advertisementDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(advertisementDtoList);
		return jDto;
	}

	@Override
	public JsonDto<AdvertisementDto> advertisementPriorityUpdate(WrapperDto _wrapperDto) {
		JsonDto<AdvertisementDto> jDto = new JsonDto<AdvertisementDto>();
		List<AdvertisementDto> advertisementDtoList = _wrapperDto.getAdvertisement();
		
		for(AdvertisementDto a : advertisementDtoList){
			Advertisement advertisement = advertisementDao.readById(a.getIdfAdvertisement());
			advertisement.setPriority(a.getPriority());			
			advertisementDao.update(advertisement);
		}
		jDto.setResultCode("S");
		// jDto.setDataObject(_pensionDto);
		return jDto;
	}

}
