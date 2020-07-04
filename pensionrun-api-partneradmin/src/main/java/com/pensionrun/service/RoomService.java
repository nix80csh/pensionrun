package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.AmenityDto;
import com.pensionrun.dto.JsonDto;

@Transactional
public interface RoomService {

	@Transactional(readOnly=true)
	JsonDto<AmenityDto> amenityList();
	
}
