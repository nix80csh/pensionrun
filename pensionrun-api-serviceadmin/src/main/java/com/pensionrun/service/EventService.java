package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.EventDto;
import com.pensionrun.dto.JsonDto;

@Transactional
public interface EventService {
	JsonDto<EventDto> eventCreate(EventDto _eventDto);
	JsonDto<EventDto> eventUpdate(EventDto _eventDto);
	JsonDto<EventDto> eventDelete(EventDto _eventDto);
	
	@Transactional(readOnly=true)
	JsonDto<EventDto> eventList();
}
