package com.pensionrun.service;

import com.pensionrun.dto.ImageDto;
import com.pensionrun.dto.JsonDto;

public interface ImageService {
	public JsonDto<String> uploadImage(ImageDto imageUploadDto);
	public JsonDto<String> deleteImage(ImageDto imageDeleteDto);
}
