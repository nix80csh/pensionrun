package com.pensionrun.dto;

import java.util.List;

import lombok.Data;
@Data
public class ImageCreateWrapperDto {
	private MoveImageDto moveImageDto;   
	private List<PensionImageDto> pensionImage;
	private List<RoomImageDto> roomImage;
	
	
}
