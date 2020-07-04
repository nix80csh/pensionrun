package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;
@Data
public class AmenityDto {
	
	private Integer idfAmenity;
	private String name;
	private Byte priority;
	private Date regDate;
	
	
}
