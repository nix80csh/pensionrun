package com.pensionrun.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.PensionImageDao;
import com.pensionrun.dao.RoomImageDao;
import com.pensionrun.dto.ImageCreateWrapperDto;
import com.pensionrun.dto.ImageDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionImageDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.entity.Pension;
import com.pensionrun.entity.PensionImage;
import com.pensionrun.util.ImageUtil;
@Service
public class PensionImageServiceImpl implements PensionImageService {
	@Autowired
	private PensionImageDao pensionImageDao;
	@Autowired
	private RoomImageDao roomImageDao;
	@Autowired
	private ImageUtil imageUtil;
	private static final Logger logger = LoggerFactory.getLogger(PensionImageServiceImpl.class);

	@Override
	public JsonDto<PensionImage> pensionImageCreate(ImageDto imageDto) {
		// TODO Auto-generated method stub
		JsonDto<PensionImage> jsonDto=new JsonDto<PensionImage>();
		PensionImage pensionImage=new PensionImage();
		Pension pension=new Pension();
		pension.setIdfPension(Integer.parseInt(imageDto.getUploadFolder()));
		pensionImage.setPension(pension);
		pensionImage.setImage(imageUtil.uploadImage(imageDto.getBucketName(),imageDto.getUploadFolder(),imageDto.getFiles(),imageDto.getType()).get(0));
		jsonDto.setDataObject(pensionImage);
		jsonDto.setResultCode("S");
		return jsonDto;
	}
	@Override
	public JsonDto<PensionImage> pensionImageListCreate(ImageCreateWrapperDto imageCreateWrapperDto) {
		// TODO Auto-generated method stub
		
		
		JsonDto<PensionImage> jsonDto=new JsonDto<PensionImage>();
		Pension pension=new Pension();
		pension.setIdfPension(imageCreateWrapperDto.getPensionImage().get(0).getIdfPension());
		for(PensionImageDto item:imageCreateWrapperDto.getPensionImage()){
			PensionImage image=new PensionImage();
			BeanUtils.copyProperties(item, image);
			image.setPension(pension);
			pensionImageDao.create(image);
		}
		imageUtil.moveImagerFolder(imageCreateWrapperDto.getMoveImageDto().getBucket(), imageCreateWrapperDto.getMoveImageDto().getOrigin(), imageCreateWrapperDto.getMoveImageDto().getTarget());
		jsonDto.setResultCode("S");
		return jsonDto;
	}

	@Override
	public JsonDto<PensionImage> pensionImageListUpdate(WrapperDto wrapperDto) {
		// TODO Auto-generated method stub
		JsonDto<PensionImage> jsonDto=new JsonDto<PensionImage>();
		Pension pension=new Pension();
		pension.setIdfPension(wrapperDto.getPensionImage().get(0).getIdfPension());
		for(PensionImageDto item:wrapperDto.getPensionImage()){
			PensionImage image=new PensionImage();
			BeanUtils.copyProperties(item, image);
			image.setPension(pension);
			pensionImageDao.update(image);
		}
		jsonDto.setResultCode("S");
		return jsonDto;
	}

	@Override
	public JsonDto<PensionImage> pensionImageUpdate(ImageDto imageDto) {
		// TODO Auto-generated method stub
		JsonDto<PensionImage> jsonDto=new JsonDto<PensionImage>();
		PensionImage pensionImage=new PensionImage();
		Pension pension=new Pension();
		pension.setIdfPension(Integer.parseInt(imageDto.getUploadFolder()));
		pensionImage.setPension(pension);
		pensionImage.setImage(imageUtil.uploadImage(imageDto.getBucketName(),imageDto.getUploadFolder(),imageDto.getFiles(),imageDto.getType()).get(0));
		jsonDto.setDataObject(pensionImageDao.create(pensionImage));
		jsonDto.setResultCode("S");
		return jsonDto;
	}
	@Override
	public JsonDto<PensionImage> pensionImageListDelete(WrapperDto wrapperDto) {
		// TODO Auto-generated method stub
		JsonDto<PensionImage> jsonDto=new JsonDto<PensionImage>();
		for(PensionImageDto item:wrapperDto.getPensionImage()){
			pensionImageDao.delete(pensionImageDao.readById(item.getIdfPensionImage()));
			imageUtil.deleteImage("pension-image.pensionrun.com", item.getIdfPension()+"/"+item.getImage());
		}
		jsonDto.setResultCode("S");
		return jsonDto;
	}
	@Override
	public JsonDto<PensionImageDto> readPensionImageByIdfPension(Integer idfPension) {
		// TODO Auto-generated method stub
		JsonDto<PensionImageDto> jsonDto=new JsonDto<PensionImageDto>();
		jsonDto.setDataList(new ArrayList<PensionImageDto>());
		for(PensionImage image :pensionImageDao.readByIdfPension(idfPension)){
			PensionImageDto item=new PensionImageDto();
			item.setPriority(image.getPriority());
			item.setIdfPensionImage(image.getIdfPensionImage());
			item.setImage(image.getImage());
			item.setIdfPension(image.getPension().getIdfPension());
			jsonDto.getDataList().add(item);
			
		}
		jsonDto.setResultCode("S");
		return jsonDto;
	}


}
