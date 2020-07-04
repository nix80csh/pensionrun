package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.ImageCreateWrapperDto;
import com.pensionrun.dto.ImageDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionImageDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.entity.PensionImage;
@Transactional
public interface PensionImageService {
	 public JsonDto<PensionImage> pensionImageCreate(ImageDto imageDto);
	 public JsonDto<PensionImage> pensionImageUpdate(ImageDto imageDto);
	 public JsonDto<PensionImage> pensionImageListUpdate(WrapperDto wrapperDto);
	 public JsonDto<PensionImage> pensionImageListCreate(ImageCreateWrapperDto imageCreateWrapperDto);
	 public JsonDto<PensionImage> pensionImageListDelete(WrapperDto wrapperDto);
	 
	@Transactional(readOnly=true)
	JsonDto<PensionImageDto> readPensionImageByIdfPension(Integer idfPension);
}
