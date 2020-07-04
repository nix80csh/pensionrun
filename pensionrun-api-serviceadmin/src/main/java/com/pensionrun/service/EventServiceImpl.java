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
public class EventServiceImpl implements EventService {
	
	@Autowired EventDao eventDao;

	@Override
	public JsonDto<EventDto> eventCreate(EventDto _eventDto) {
		JsonDto<EventDto> jDto = new JsonDto<EventDto>();
		Event event = new Event();		
		
		BeanUtils.copyProperties(_eventDto, event);
		eventDao.create(event);
		_eventDto.setIdfEvent(event.getIdfEvent());

		jDto.setResultCode("S");
		jDto.setDataObject(_eventDto);
		return jDto;
	}

	@Override
	public JsonDto<EventDto> eventUpdate(EventDto _eventDto) {
		JsonDto<EventDto> jDto = new JsonDto<EventDto>();
		Event event = new Event();				
		BeanUtils.copyProperties(_eventDto, event);
		eventDao.update(event);
		jDto.setResultCode("S");
		// jDto.setDataObject(_pensionDto);
		return jDto;
	}

	@Override
	public JsonDto<EventDto> eventDelete(EventDto _eventDto) {
		JsonDto<EventDto> jDto = new JsonDto<EventDto>();
		Event event = eventDao.readById(_eventDto.getIdfEvent());

		if (event == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist Event");
		} else {
			eventDao.delete(event);
			jDto.setResultCode("S");
		}
		return jDto;
	}

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
