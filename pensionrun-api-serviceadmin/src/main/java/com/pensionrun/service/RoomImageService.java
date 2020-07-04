package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.ImageCreateWrapperDto;
import com.pensionrun.dto.ImageDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RoomImageDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.entity.RoomImage;
@Transactional
public interface RoomImageService {

	public JsonDto<RoomImage> roomImageCreate(ImageDto imageDto);
	public JsonDto<RoomImage> roomImageUpdate(ImageDto imageDto);
	public JsonDto<RoomImage> roomImageListCreate(ImageCreateWrapperDto imageCreateWrapperDto);
	public JsonDto<RoomImage> roomImageListUpdate(WrapperDto wrapperDto);
	public JsonDto<RoomImage> roomImageListDelete(WrapperDto wrapperDto);
	 
	@Transactional(readOnly=true)
	public JsonDto<RoomImageDto> readRoomImageByIdfRoom(Integer idfRoom); 


}
