package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.ReviewDto;

@Transactional
public interface ReviewService {
	JsonDto<ReviewDto> reviewCreate(ReviewDto _reviewDto);	
	JsonDto<ReviewDto> reviewDelete(ReviewDto _reviewDto);
}
