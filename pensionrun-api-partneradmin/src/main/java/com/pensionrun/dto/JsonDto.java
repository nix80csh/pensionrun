package com.pensionrun.dto;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class JsonDto<entityType> {
	
	private String resultCode;
	private String resultMessage;
	private entityType dataObject;
	private List<entityType> dataList;
	
}


