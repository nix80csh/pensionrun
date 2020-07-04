package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RecommendationDto;
import com.pensionrun.service.RecommendationService;

@Controller
@RequestMapping(value = "/recommendation")
public class RecommendationController {
	
	@Autowired
	RecommendationService recommendationService;

	@ResponseBody
	@RequestMapping(value = "/recommendatioinList", method = RequestMethod.GET)
	public ResponseEntity<Object> recommendatioinList() {		
		JsonDto<RecommendationDto> jDto = recommendationService.recommendationList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
}
