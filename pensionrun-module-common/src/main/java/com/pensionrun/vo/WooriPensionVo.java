package com.pensionrun.vo;

import java.util.Date;

import lombok.Data;
@Data
public class WooriPensionVo {
	private Integer idfOrder;
	private String idfProvider;
	private String idfProvierRoom;
	private String name;
	private String email;
	private String mobile;
	private String checkinDate;
	private String checkoutDate;
	private Date regDate;
	private byte peopleCount;
	private float discount;
	private String type;
	private float amount;
	private float extraAmount;
	private String reservationCode;
	private int refund;
	
	private String result;
	private String message;
	
	
}
