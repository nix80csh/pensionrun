package com.pensionrun.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PensionProvideDto {

	private Integer idfPension;
	private Integer idfProvide;
	private float price;
	private String description;
	private String name;
	
	

}
