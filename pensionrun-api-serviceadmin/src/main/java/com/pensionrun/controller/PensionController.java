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

import com.pensionrun.dto.Area1Dto;
import com.pensionrun.dto.Area2Dto;
import com.pensionrun.dto.FacilityDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionDto;
import com.pensionrun.dto.PensionFacilityDto;
import com.pensionrun.dto.PensionProvideDto;
import com.pensionrun.dto.PensionThemeDto;
import com.pensionrun.dto.ProvideDto;
import com.pensionrun.dto.ThemeDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.service.PensionService;

@Controller
@RequestMapping(value = "/pension")
public class PensionController {
	
	@Autowired
	PensionService pensionService;	
	
	@ResponseBody
	@RequestMapping(value = "/pensionCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionCreate(@RequestBody PensionDto pensionDto) {
		JsonDto<PensionDto> jDto = pensionService.pensionCreate(pensionDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pensionUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionUpdate(@RequestBody PensionDto pensionDto) {
		JsonDto<PensionDto> jDto = pensionService.pensionUpdate(pensionDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pensionDelete", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionDelete(@RequestBody PensionDto pensionDto) {
		JsonDto<PensionDto> jDto = pensionService.pensionDelete(pensionDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}	
	
	@ResponseBody
	@RequestMapping(value = "/pensionFacilityCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionFacilityCreate(@RequestBody WrapperDto wrapperDto) {		
		JsonDto<PensionFacilityDto> jDto = pensionService.pensionFacilityCreate(wrapperDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/pensionProvideCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionProvideCreate(@RequestBody WrapperDto wrapperDto) {		
		JsonDto<PensionProvideDto> jDto = pensionService.pensionProvideCreate(wrapperDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pensionThemeCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionThemeCreate(@RequestBody WrapperDto wrapperDto) {		
		JsonDto<PensionThemeDto> jDto = pensionService.pensionThemeCreate(wrapperDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/readPensionByIdfPension/{idfPension}", method = RequestMethod.GET)
	public ResponseEntity<Object> readPensionByIdfPension(@PathVariable Integer idfPension) {		
		JsonDto<PensionDto> jDto = pensionService.readPensionByIdfPension(idfPension);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/readPensionThemeByIdfPension/{idfPension}", method = RequestMethod.GET)
	public ResponseEntity<Object> readPensionThemeByIdfPension(@PathVariable Integer idfPension) {		
		JsonDto<PensionThemeDto> jDto = pensionService.readPensionThemeByIdfPension(idfPension);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/readPensionFacilityByIdfPension/{idfPension}", method = RequestMethod.GET)
	public ResponseEntity<Object> readPensionFacilityByIdfPension(@PathVariable Integer idfPension) {		
		JsonDto<PensionFacilityDto> jDto = pensionService.readPensionFacilityByIdfPension(idfPension);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/readPensionProvideByIdfPension/{idfPension}", method = RequestMethod.GET)
	public ResponseEntity<Object> readPensionProvideByIdfPension(@PathVariable Integer idfPension) {		
		JsonDto<PensionProvideDto> jDto = pensionService.readPensionProvideByIdfPension(idfPension);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pensionList", method = RequestMethod.GET)
	public ResponseEntity<Object> pensionList() {		
		JsonDto<PensionDto> jDto = pensionService.pensionList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
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
