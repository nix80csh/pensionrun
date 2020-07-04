package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.EventDao;
import com.pensionrun.dto.EventDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.entity.Event;

@Service
public class EventServiceImpl implements EventService{

	@Autowired
	EventDao eventDao;
	
	@Override
	public JsonDto<EventDto> eventList() {
		JsonDto<EventDto> jDto = new JsonDto<EventDto>();
		List<EventDto> eventDtoList = new ArrayList<EventDto>();
		List<Event> eventList = eventDao.readAll();

		for (Event e : eventList) {
			EventDto eventDto = new EventDto();
			
			BeanUtils.copyProperties(e, eventDto);
			eventDtoList.add(eventDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(eventDtoList);
		return jDto;
	}

}
