package com.pensionrun.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AuthAppDto {
	
	private Integer idfAccount;
	private String userid;
	private String password;
	private String accessToken;
	private String accessTokenType;
	
	

	
	
}
