package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.EventDto;
import com.pensionrun.dto.JsonDto;

@Transactional
public interface EventService {
	@Transactional(readOnly=true)
	JsonDto<EventDto> eventList();
}
