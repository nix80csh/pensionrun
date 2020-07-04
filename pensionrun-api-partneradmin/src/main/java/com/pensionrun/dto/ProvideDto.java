package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;
@Data
public class ProvideDto {
	
	private Integer idfProvide;
	private String name;
	private Byte priority;
	private Date regDate;

}
