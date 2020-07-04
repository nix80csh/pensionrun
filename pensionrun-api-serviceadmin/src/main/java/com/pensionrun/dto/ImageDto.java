package com.pensionrun.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class ImageDto {
	private String bucketName;
	private String uploadFolder;
	private MultipartFile[] files;
	private String type;
	private String image;
	
}
