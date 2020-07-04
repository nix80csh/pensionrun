package com.pensionrun.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PensionFacilityDto {
	
	
	private Integer idfPension;
	private Integer idfFacility;
	private float price;
	private String description;
	private String name;
	
}
