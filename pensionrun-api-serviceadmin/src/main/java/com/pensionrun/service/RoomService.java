package com.pensionrun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.AmenityDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RoomAmenityDto;
import com.pensionrun.dto.RoomDto;
import com.pensionrun.dto.RoomPriceDto;
import com.pensionrun.dto.WrapperDto;

@Transactional
public interface RoomService {
	JsonDto<RoomDto> roomCreate(RoomDto _roomDto);	
	JsonDto<RoomDto> roomUpdate(RoomDto _roomDto);	
	JsonDto<RoomDto> roomDelete(RoomDto _roomDto);
	
	JsonDto<RoomPriceDto> roomPriceCreate(WrapperDto _wrapperDto);	
	JsonDto<RoomDto> roomPriorityUpdate(WrapperDto _wrapperDto);
	JsonDto<RoomAmenityDto> roomAmenityCreate(WrapperDto _wrapperDto);
	
	JsonDto<RoomDto> readRoomByIdfPension(Integer _idfPension);
	JsonDto<RoomPriceDto> readRoomPriceListByCheckDate(RoomPriceDto _roomPriceDto);	
	JsonDto<RoomAmenityDto> readAmenityByIdfRoom(Integer _idfRoom);
	
	@Transactional(readOnly=true)
	JsonDto<AmenityDto> amenityList();
	
}
