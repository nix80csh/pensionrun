package com.pensionrun.dto;

import java.util.Date;



import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PensionInquiryDto {
	
	private Integer idfPensionInquiry;
	private String name;
	private String area;
	private String phone;
	private Date regDate;
	
	
}
