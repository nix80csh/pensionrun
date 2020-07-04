package com.pensionrun.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ReviewDto {
	
	
	private Integer idfReview;
	private Integer idfAccount;
	private Integer idfPension;
	private String content;
	@Min(0) @Max(5)
	private byte pointClean;
	@Min(0) @Max(5)
	private byte pointLocation;
	@Min(0) @Max(5)
	private byte pointService;
	private MultipartFile[] files;
	private Date regDate;	
	
}
