package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;
@Data
public class RoomImageDto {
	private Integer idfRoomImage;
	private Integer idfRoom;
	private String image;
	private Byte priority;
	private Date regDate;
	
	
}
