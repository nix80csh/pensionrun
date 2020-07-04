package com.pensionrun.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AppInfoDto {
	
	private String version;
	private char typeOs;
	private String urlDownload;
	private String description;
	private char state;
	private Date regDate;
	
	
}
