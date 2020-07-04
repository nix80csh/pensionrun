package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RecommendationDto;
import com.pensionrun.dto.RecommendationPensionDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.service.RecommendationService;

@Controller
@RequestMapping(value = "/recommendation")
public class RecommendationController {
	
	@Autowired RecommendationService recommendationService;
	
	@ResponseBody
	@RequestMapping(value = "/recommendationCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> recommendationCreate(@RequestBody RecommendationDto recommendationDto) {
		JsonDto<RecommendationDto> jDto = recommendationService.recommendationCreate(recommendationDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommendationUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> recommendationUpdate(@RequestBody RecommendationDto recommendationDto) {
		JsonDto<RecommendationDto> jDto = recommendationService.recommendationUpdate(recommendationDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommendationPriorityUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> recommendationPriorityUpdate(@RequestBody WrapperDto wrapperDto) {
		JsonDto<RecommendationDto> jDto = recommendationService.advertisementPriorityUpdate(wrapperDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/recommendationDelete", method = RequestMethod.POST)
	public ResponseEntity<Object> recommendationDelete(@RequestBody RecommendationDto recommendationDto) {
		JsonDto<RecommendationDto> jDto = recommendationService.recommendationDelete(recommendationDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommendationList", method = RequestMethod.GET)
	public ResponseEntity<Object> recommendationList() {
		JsonDto<RecommendationDto> jDto = recommendationService.recommendationList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommendationPensionCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> recommendationPensionCreate(@RequestBody WrapperDto wrapperDto) {		
		JsonDto<RecommendationPensionDto> jDto = recommendationService.recommendationPensionCreate(wrapperDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recommendationPensionByIdfRecommendation/{idfRecommendation}", method = RequestMethod.GET)
	public ResponseEntity<Object> recommendationPensionByIdfRecommendation(@PathVariable Integer idfRecommendation) {		
		JsonDto<RecommendationPensionDto> jDto = recommendationService.readPensionProvideByIdfPension(idfRecommendation);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
}
