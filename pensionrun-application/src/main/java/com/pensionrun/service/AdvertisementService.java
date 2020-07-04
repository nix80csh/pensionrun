package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.AdvertisementDto;
import com.pensionrun.dto.JsonDto;

@Transactional
public interface AdvertisementService {
	
	@Transactional(readOnly=true)
	JsonDto<AdvertisementDto> advertisementList(char _type);

}
