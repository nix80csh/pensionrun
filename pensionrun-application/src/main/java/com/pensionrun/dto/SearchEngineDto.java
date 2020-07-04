package com.pensionrun.dto;

import java.util.List;

import lombok.Data;
@Data
public class SearchEngineDto {

	private List<SearchEngineArea2Dto> area2;
	private List<SearchEnginePensionDto> pension;

	
}
