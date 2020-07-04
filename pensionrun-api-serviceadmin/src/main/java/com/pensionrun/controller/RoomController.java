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

import com.pensionrun.dto.AmenityDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RoomAmenityDto;
import com.pensionrun.dto.RoomDto;
import com.pensionrun.dto.RoomPriceDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.service.RoomService;

@Controller
@RequestMapping(value = "/room")
public class RoomController {

	@Autowired RoomService roomService;
	
	
	@ResponseBody
	@RequestMapping(value = "/roomCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> roomCreate(@RequestBody RoomDto roomDto) {		
		JsonDto<RoomDto> jDto = roomService.roomCreate(roomDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/roomUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> roomUpdate(@RequestBody RoomDto roomDto) {		
		JsonDto<RoomDto> jDto = roomService.roomUpdate(roomDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/roomPriorityUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> roomPriorityUpdate(@RequestBody WrapperDto wrapperDto) {		
		JsonDto<RoomDto> jDto = roomService.roomPriorityUpdate(wrapperDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/roomDelete", method = RequestMethod.POST)
	public ResponseEntity<Object> roomDelete(@RequestBody RoomDto roomDto) {		
		JsonDto<RoomDto> jDto = roomService.roomDelete(roomDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/roomAmenityCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> roomAmenityCreate(@RequestBody WrapperDto wrapperDto) {		
		JsonDto<RoomAmenityDto> jDto = roomService.roomAmenityCreate(wrapperDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/readRoomByIdfPension/{idfPension}", method = RequestMethod.GET)
	public ResponseEntity<Object> readRoomByIdfPension(@PathVariable Integer idfPension) {		
		JsonDto<RoomDto> jDto = roomService.readRoomByIdfPension(idfPension);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/readAmenityByIdfRoom/{idfRoom}", method = RequestMethod.GET)
	public ResponseEntity<Object> readAmenityByIdfRoom(@PathVariable Integer idfRoom) {		
		JsonDto<RoomAmenityDto> jDto = roomService.readAmenityByIdfRoom(idfRoom);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/amenityList", method = RequestMethod.GET)
	public ResponseEntity<Object> amenityList() {		
		JsonDto<AmenityDto> jDto = roomService.amenityList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/roomPriceCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> roomPriceCreate(@RequestBody WrapperDto wrapperDto) {		
		JsonDto<RoomPriceDto> jDto = roomService.roomPriceCreate(wrapperDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/readRoomPriceByCheckDate", method = RequestMethod.POST)
	public ResponseEntity<Object> readRoomPriceByCheckDate(@RequestBody RoomPriceDto roomPriceDto) {		
		JsonDto<RoomPriceDto> jDto = roomService.readRoomPriceListByCheckDate(roomPriceDto);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
}
