package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;
@Data
public class ThemeDto {
	private Integer idfTheme;
	private String name;
	private Byte priority;
	private Date regDate;
	
}
