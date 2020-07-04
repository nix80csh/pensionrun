package com.pensionrun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dto.ImageDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.util.ImageUtil;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageUtil imageUtil;
	@Override
	public JsonDto<String> uploadImage(ImageDto imageUploadDto) {
		// TODO Auto-generated method stub
		JsonDto<String> jsonDto=new JsonDto<String>();
		try{
			jsonDto.setDataObject(imageUtil.uploadImage(imageUploadDto.getBucketName(), imageUploadDto.getUploadFolder(), imageUploadDto.getFiles(), imageUploadDto.getType()).get(0));
			jsonDto.setResultCode("S");
		}catch(Exception e){
			jsonDto.setResultCode("F");
		}
		return jsonDto;
	}

	@Override
	public JsonDto<String> deleteImage(ImageDto imageDeleteDto) {
		// TODO Auto-generated method stub
		JsonDto<String> jsonDto=new JsonDto<String>();
		try{
			imageUtil.deleteImage(imageDeleteDto.getBucketName(), imageDeleteDto.getUploadFolder()+"/"+imageDeleteDto.getImage());
		}catch(Exception e){
			jsonDto.setResultCode("F");
		}
		return jsonDto;
	}

}
