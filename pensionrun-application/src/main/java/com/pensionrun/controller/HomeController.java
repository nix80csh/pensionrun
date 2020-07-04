package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.AdvertisementDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.service.AdvertisementService;

@Controller
@RequestMapping(value = "home")
public class HomeController {

	@Autowired
	AdvertisementService advertisementService;
	
	@ResponseBody
	@RequestMapping(value = "/advertisementList/{type}", method = RequestMethod.GET)
	public ResponseEntity<Object> advertisementList(@PathVariable char type) {		
		JsonDto<AdvertisementDto> jDto = advertisementService.advertisementList(type);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Object> home() {	
		return new ResponseEntity<Object>("isAuthenticated", HttpStatus.OK);
	}
}
