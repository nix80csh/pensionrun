package com.pensionrun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.pensionrun.service.PensionImageService;

@Controller
@RequestMapping("/image")
public class PensionImageController {
	private static final Logger logger = LoggerFactory.getLogger(PensionImageController.class);

	@Autowired
	private PensionImageService pensionImageServicel;
	@ResponseBody
	@RequestMapping(value = "/pensionImageCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionImageCreate(ImageDto imageDto) {	
		return new ResponseEntity<Object>(pensionImageServicel.pensionImageCreate(imageDto), HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping(value = "/pensionImageUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionImageUpdate(ImageDto imageDto) {	
		return new ResponseEntity<Object>(pensionImageServicel.pensionImageUpdate(imageDto), HttpStatus.OK);
	}


	@ResponseBody
	@RequestMapping(value = "/pensionImageListCreate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionImageListCreate(@RequestBody  ImageCreateWrapperDto imageCreateWrapperDto) {	
		return new ResponseEntity<Object>(pensionImageServicel.pensionImageListCreate(imageCreateWrapperDto), HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping(value = "/pensionImageListUpdate", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionImageListUpdate(@RequestBody  WrapperDto wrapperDto) {	
		logger.info(wrapperDto.getPensionImage().toString());
		return new ResponseEntity<Object>(pensionImageServicel.pensionImageListUpdate(wrapperDto), HttpStatus.OK);
	}



	@ResponseBody
	@RequestMapping(value = "/readPensionImageByIdfPension/{idfPension}", method = RequestMethod.GET)
	public ResponseEntity<Object> readPensionThemeByIdfPension(@PathVariable Integer idfPension) {		
		return new ResponseEntity<Object>(pensionImageServicel.readPensionImageByIdfPension(idfPension), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pensionImageListDelete", method = RequestMethod.POST)
	public ResponseEntity<Object> pensionImageDelete(@RequestBody  WrapperDto wrapperDto) {
		return new ResponseEntity<Object>(pensionImageServicel.pensionImageListDelete(wrapperDto), HttpStatus.OK);
	}
	


}
