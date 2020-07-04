package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;
@Data
public class NoticeDto {
	private Integer idfNotice;
	private Integer idfAdmin;
	private String subject;
	private String content;
	private Date regDate;
	
}
