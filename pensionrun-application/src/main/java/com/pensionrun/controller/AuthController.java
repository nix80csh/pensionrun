package com.pensionrun.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.AppValidationDto;
import com.pensionrun.dto.AuthAppDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.service.AuthService;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired AuthService authService; 
	
	@ResponseBody
	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
	public ResponseEntity<Object> sendSMS(@RequestBody AppValidationDto appValidationDto) {		
		JsonDto<AppValidationDto> jDto = authService.sendSMS(appValidationDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateSMSCode", method = RequestMethod.POST)
	public ResponseEntity<Object> validateSMSCode(@RequestBody AppValidationDto appValidationDto) {		
		JsonDto<AppValidationDto> jDto = authService.validateSMSCode(appValidationDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateEmail", method = RequestMethod.POST)
	public ResponseEntity<Object> validateEmail(@RequestBody AppValidationDto appValidationDto) {		
		JsonDto<AppValidationDto> jDto = authService.validateEmail(appValidationDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/prLogIn", method = RequestMethod.POST)
	public ResponseEntity<Object> prLogIn(@RequestBody AuthAppDto authAppDto, HttpServletResponse res) {		
		JsonDto<AuthAppDto> jDto = authService.prAuth(authAppDto.getUserid(), authAppDto.getPassword(), res);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/snsLogIn", method = RequestMethod.POST)
	public ResponseEntity<Object> snsLogIn(@RequestBody AuthAppDto authAppDto, HttpServletResponse res) {		
		JsonDto<AuthAppDto> jDto = authService.snsAuth(authAppDto.getAccessToken(), authAppDto.getAccessTokenType(), res);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
}
