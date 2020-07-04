package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.Area1Dto;
import com.pensionrun.dto.Area2Dto;
import com.pensionrun.dto.FacilityDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.ProvideDto;
import com.pensionrun.dto.ThemeDto;
import com.pensionrun.service.PensionService;

@Controller
@RequestMapping(value = "/pension")
public class PensionController {
	
	@Autowired
	PensionService pensionService;	
	

	
	@ResponseBody
	@RequestMapping(value = "/area1List", method = RequestMethod.GET)
	public ResponseEntity<Object> area1List() {		
		JsonDto<Area1Dto> jDto = pensionService.area1List();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/area2List", method = RequestMethod.GET)
	public ResponseEntity<Object> area2List() {		
		JsonDto<Area2Dto> jDto = pensionService.area2List();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/facilityList", method = RequestMethod.GET)
	public ResponseEntity<Object> facilityList() {		
		JsonDto<FacilityDto> jDto = pensionService.facilityList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/provideList", method = RequestMethod.GET)
	public ResponseEntity<Object> provideList() {		
		JsonDto<ProvideDto> jDto = pensionService.provideList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
		
	@ResponseBody
	@RequestMapping(value = "/themeList", method = RequestMethod.GET)
	public ResponseEntity<Object> themeList() {		
		JsonDto<ThemeDto> jDto = pensionService.themeList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
	
	

}
