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

import com.pensionrun.dto.ImageCreateWrapperDto;
import com.pensionrun.dto.ImageDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.service.RoomImageService;

@Controller
@RequestMapping("/image")
public class RoomImageController {
	@Autowired
	private RoomImageService roomImageServicel;
	@ResponseBody
	@RequestMapping(value = "/roomImageCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> roomImageCreate(ImageDto imageDto) {	
		return new ResponseEntity<Object>(roomImageServicel.roomImageCreate(imageDto), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/roomImageUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionImageUpdate(ImageDto imageDto) {	
		return new ResponseEntity<Object>(roomImageServicel.roomImageUpdate(imageDto), HttpStatus.OK);
	}


	@ResponseBody
	@RequestMapping(value = "/roomImageListCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionImageListCreate(@RequestBody  ImageCreateWrapperDto imageCreateWrapperDto) {	
		return new ResponseEntity<Object>(roomImageServicel.roomImageListCreate(imageCreateWrapperDto), HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping(value = "/roomImageListUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionImageListUpdate(@RequestBody  WrapperDto wrapperDto) {	
		return new ResponseEntity<Object>(roomImageServicel.roomImageListUpdate(wrapperDto), HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/roomImageListDelete", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionImageDelete(@RequestBody  WrapperDto wrapperDto) {
		System.out.println(wrapperDto.getRoomImage().toString());
		return new ResponseEntity<Object>(roomImageServicel.roomImageListDelete(wrapperDto), HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/readRoomImageByIdfRoom/{idfRoom}", method = RequestMethod.GET)
	public ResponseEntity<Object> readPensionImageByIdfRoom(@PathVariable Integer idfRoom) {	
	
		return new ResponseEntity<Object>( roomImageServicel.readRoomImageByIdfRoom(idfRoom), HttpStatus.OK);
	}
	

}
