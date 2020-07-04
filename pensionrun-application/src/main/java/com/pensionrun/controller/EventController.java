package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.EventDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.service.EventService;


@Controller
@RequestMapping(value = "/event")
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@ResponseBody
	@RequestMapping(value = "/eventList", method = RequestMethod.GET)
	public ResponseEntity<Object> eventList() {		
		JsonDto<EventDto> jDto = eventService.eventList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
}
