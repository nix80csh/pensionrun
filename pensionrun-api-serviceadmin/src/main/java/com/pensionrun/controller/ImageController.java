package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.ImageDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.service.ImageService;

@Controller
@RequestMapping("/image")
public class ImageController {
	@Autowired
	private ImageService imageService;
	@ResponseBody
	@RequestMapping(value="/uploadImage",method = RequestMethod.POST)
	public JsonDto<String> uploadImage(ImageDto imageUploadDto){
		System.out.println(imageUploadDto.toString());
		return imageService.uploadImage(imageUploadDto);
	}
	@ResponseBody
	@RequestMapping(value="/deleteImage",method = RequestMethod.POST)
	public JsonDto<String> deleteImage(@RequestBody ImageDto imageDeleteDto){
		System.out.println(imageDeleteDto.toString());
		return imageService.deleteImage(imageDeleteDto);
	}
}
