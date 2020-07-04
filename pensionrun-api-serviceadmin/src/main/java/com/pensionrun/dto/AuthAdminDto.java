package com.pensionrun.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AuthAdminDto {
	private Integer idfAdmin;
	private String xAuthToken;
	
}
