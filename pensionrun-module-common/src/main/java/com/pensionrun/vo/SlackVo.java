package com.pensionrun.vo;

import java.util.Date;

import lombok.Data;
@Data
public class SlackVo {
	private String name;
	private Date regDate;
	private String checkinDate;
	private char state;
	private String reservationCode;
	private Integer idfOrder;
	private String noitce;
	private String color;
	private String message;
	

}
