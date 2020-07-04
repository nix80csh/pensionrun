package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;
@Data
public class EventDto {
	
	private Integer idfEvent;
	private String title;
	private String dateStart;
	private String dateEnd;
	private String image;
	private String urlContent;
	private String urlResult;
	private Date regDate;
	
	
	
	
}
