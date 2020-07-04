package com.pensionrun.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RecommendationDto {
	
	private int idfRecommendation;
	private String title;
	private String titleSub;
	private String image;
	private Byte priority;
	private Long pensionCount;
	private Date regDate;
	
	
}
