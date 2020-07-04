package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.AdvertisementDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.service.AdvertisementService;

@Controller
@RequestMapping(value = "/advertisement")
public class AdvertisementController {
		
	@Autowired
	AdvertisementService advertisementService;
	
	@ResponseBody
	@RequestMapping(value = "/advertisementCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> advertisementCreate(@RequestBody AdvertisementDto advertisementDto) {
		JsonDto<AdvertisementDto> jDto = advertisementService.advertisementCreate(advertisementDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/advertisementUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> advertisementUpdate(@RequestBody AdvertisementDto advertisementDto) {
		JsonDto<AdvertisementDto> jDto = advertisementService.advertisementUpdate(advertisementDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}	
	
	@ResponseBody
	@RequestMapping(value = "/advertisementPriorityUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> advertisementPriorityUpdate(@RequestBody WrapperDto wrapperDto) {
		JsonDto<AdvertisementDto> jDto = advertisementService.advertisementPriorityUpdate(wrapperDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/advertisementDelete", method = RequestMethod.POST)
	public ResponseEntity<Object> advertisementDelete(@RequestBody AdvertisementDto advertisementDto) {
		JsonDto<AdvertisementDto> jDto = advertisementService.advertisementDelete(advertisementDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/advertisementList", method = RequestMethod.GET)
	public ResponseEntity<Object> advertisementList() {
		JsonDto<AdvertisementDto> jDto = advertisementService.advertisementList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
}
