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

import com.pensionrun.dto.AuthAdminDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.entity.Admin;
import com.pensionrun.service.AuthService;


@Controller
@RequestMapping(value = "/auth")
public class AuthController {
	
	
	@Autowired
	AuthService authService;

	@ResponseBody
	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
	public ResponseEntity<Object> logIn(@RequestBody Admin admin, HttpServletResponse res) {		
		JsonDto<AuthAdminDto> jDto = authService.auth(admin.getEmail(), admin.getPassword(), res);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> home() {	
		return new ResponseEntity<String>("test", HttpStatus.OK);
	}

}
