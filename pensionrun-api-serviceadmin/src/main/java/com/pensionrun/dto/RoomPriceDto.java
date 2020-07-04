package com.pensionrun.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RoomPriceDto {
	private Integer idfRoom;
	private String checkDate;	
	private Character type;
	private Float price;
	private Float priceDiscount;
	private byte remainRoomCount;
	private Character state;
	private String startCheckDate;
	private String endCheckDate;	
	private Date regDate;
	
	
}
