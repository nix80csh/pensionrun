package com.pensionrun.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AdvertisementDto {
	
	private Integer idfAdvertisement;
	private Integer idfPension;
	private String pensionName;
	private String title;
	private String image;
	private String keyword;
	private char type;
	private char state;
	private Byte priority;
	private Date regDate;
	
	
	
}
