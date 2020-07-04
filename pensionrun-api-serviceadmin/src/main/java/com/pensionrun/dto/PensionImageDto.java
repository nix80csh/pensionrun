package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;
@Data
public class PensionImageDto {
	private Integer idfPensionImage;
	private Integer idfPension;
	private String image;
	private Byte priority;
	private Date regDate;
	
	
	
}
