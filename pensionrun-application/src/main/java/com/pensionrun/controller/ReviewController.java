package com.pensionrun.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.ReviewDto;
import com.pensionrun.service.ReviewService;

@Controller
@RequestMapping(value = "/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@ResponseBody
	@RequestMapping(value = "/reviewCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> reviewCreate(@Valid ReviewDto reviewDto) {		
		JsonDto<ReviewDto> jDto = reviewService.reviewCreate(reviewDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/reviewDelete", method = RequestMethod.POST)
	public ResponseEntity<Object> reviewDelete(@RequestBody ReviewDto reviewDto) {
		JsonDto<ReviewDto> jDto = reviewService.reviewDelete(reviewDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
}
