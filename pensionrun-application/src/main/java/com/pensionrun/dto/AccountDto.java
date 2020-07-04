package com.pensionrun.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AccountDto {
	
	private Integer idfAccount;
	private String userid;
	private String password;
	private String name;
	private String email;
	private char emailReception;
	private float coin;
	private String mobile;
	private String urlImageProfile;
	private char typeOs;
	private char typeServicePlatform;
	private char state;
	private Date regDate;
	
}
