package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;
@Data
public class FacilityDto {
	
	private Integer idfFacility;
	private String name;
	private Byte priority;
	private Date regDate;
	

}
