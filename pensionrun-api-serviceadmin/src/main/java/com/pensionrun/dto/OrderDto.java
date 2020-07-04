package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;
@Data
public class OrderDto {
	private Integer idfOrder;
	private Integer idfAccount;
	private Integer idfRoom;
	private String name;
	private String mobile;
	private String email;
	private char state;
	private byte peopleCount;
	private float amount;
	private String checkinDate;
	private String checkoutDate;
	private String reservationCode;
	private Date regDate;
	private String pensionName;
	private String roomName;
	private boolean isReservation;
}
