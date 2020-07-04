package com.pensionrun.dto;

import lombok.Data;

@Data
public class PensionCountPerAreaListDto {
	
	private Integer idfArea1;
	private String a1Name;
	private Integer idfArea2;
	private String a2Name;
	private Long count;
	private Long checkTodayNewPensionCount;
	
	
	
	
}
