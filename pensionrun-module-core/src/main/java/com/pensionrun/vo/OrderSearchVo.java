package com.pensionrun.vo;

import lombok.Data;

@Data
public class OrderSearchVo extends SearchVo {
	private String name;
	private String mobile;
	private char state;
	private String checkinDate;
	private String checkoutDate;
	private String reservationCode;
	private String startRegDate;
	private String endRegDate;
	
	
}
