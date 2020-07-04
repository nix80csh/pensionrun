package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	@RequestMapping(value = "/eventCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> eventCreate(@RequestBody EventDto eventDto) {
		JsonDto<EventDto> jDto = eventService.eventCreate(eventDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/eventUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> eventUpdate(@RequestBody EventDto eventDto) {
		JsonDto<EventDto> jDto = eventService.eventUpdate(eventDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/eventDelete", method = RequestMethod.POST)
	public ResponseEntity<Object> eventDelete(@RequestBody EventDto eventDto) {
		JsonDto<EventDto> jDto = eventService.eventDelete(eventDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/eventList", method = RequestMethod.GET)
	public ResponseEntity<Object> eventList() {
		 JsonDto<EventDto> jDto = eventService.eventList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}

}
