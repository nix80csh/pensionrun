package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.AmenityDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.service.RoomService;

@Controller
@RequestMapping(value = "/room")
public class RoomController {

	@Autowired RoomService roomService;
	
	
	@ResponseBody
	@RequestMapping(value = "/amenityList", method = RequestMethod.GET)
	public ResponseEntity<Object> amenityList() {		
		JsonDto<AmenityDto> jDto = roomService.amenityList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
}
