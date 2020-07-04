package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.AdvertisementDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RoomDto;
import com.pensionrun.dto.WrapperDto;

@Transactional
public interface AdvertisementService {
	
	JsonDto<AdvertisementDto> advertisementCreate(AdvertisementDto _AdvertisementDto);
	JsonDto<AdvertisementDto> advertisementUpdate(AdvertisementDto _AdvertisementDto);	
	JsonDto<AdvertisementDto> advertisementDelete(AdvertisementDto _AdvertisementDto);	
	
	JsonDto<AdvertisementDto> advertisementPriorityUpdate(WrapperDto _wrapperDto);
	
	@Transactional(readOnly=true)
	JsonDto<AdvertisementDto> advertisementList();
	
}
