package com.pensionrun.service;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.RoomImageDao;
import com.pensionrun.dto.ImageCreateWrapperDto;
import com.pensionrun.dto.ImageDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RoomImageDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.entity.Room;
import com.pensionrun.entity.RoomImage;
import com.pensionrun.util.ImageUtil;
@Service
public class RoomImageServiceImpl implements RoomImageService {

	@Autowired
	private RoomImageDao roomImageDao;
	@Autowired
	private ImageUtil imageUtil;

	@Override
	public JsonDto<RoomImage> roomImageCreate(ImageDto imageDto) {
		// TODO Auto-generated method stub
		JsonDto<RoomImage> jsonDto=new JsonDto<RoomImage>();
		RoomImage roomImage=new RoomImage();
		Room room=new Room();
		room.setIdfRoom(Integer.parseInt(imageDto.getUploadFolder()));
		roomImage.setRoom(room);
		roomImage.setImage(imageUtil.uploadImage(imageDto.getBucketName(),imageDto.getUploadFolder(),imageDto.getFiles(),imageDto.getType()).get(0));
		jsonDto.setDataObject(roomImage);
		jsonDto.setResultCode("S");
		return jsonDto;
	}

	@Override
	public JsonDto<RoomImage> roomImageUpdate(ImageDto imageDto) {
		// TODO Auto-generated method stub
		JsonDto<RoomImage> jsonDto=new JsonDto<RoomImage>();
		RoomImage roomImage=new RoomImage();
		Room room=new Room();
		room.setIdfRoom(Integer.parseInt(imageDto.getUploadFolder()));
		roomImage.setRoom(room);
		roomImage.setImage(imageUtil.uploadImage(imageDto.getBucketName(),imageDto.getUploadFolder(),imageDto.getFiles(),imageDto.getType()).get(0));
		jsonDto.setDataObject(roomImageDao.create(roomImage));
		jsonDto.setResultCode("S");
		return jsonDto;
	}

	@Override
	public JsonDto<RoomImage> roomImageListCreate(ImageCreateWrapperDto imageCreateWrapperDto) {
		// TODO Auto-generated method stub
		JsonDto<RoomImage> jsonDto=new JsonDto<RoomImage>();
		Room room=new Room();
		room.setIdfRoom(imageCreateWrapperDto.getRoomImage().get(0).getIdfRoom());
		for(RoomImageDto item:imageCreateWrapperDto.getRoomImage()){
			RoomImage image=new RoomImage();
			BeanUtils.copyProperties(item, image);
			image.setRoom(room);
			roomImageDao.create(image);
		}
		imageUtil.moveImagerFolder(imageCreateWrapperDto.getMoveImageDto().getBucket(), imageCreateWrapperDto.getMoveImageDto().getOrigin(), imageCreateWrapperDto.getMoveImageDto().getTarget());
		jsonDto.setResultCode("S");
		return jsonDto;
	}

	@Override
	public JsonDto<RoomImage> roomImageListUpdate(WrapperDto wrapperDto) {
		// TODO Auto-generated method stub
		JsonDto<RoomImage> jsonDto=new JsonDto<RoomImage>();
		Room room=new Room();
		room.setIdfRoom(wrapperDto.getRoomImage().get(0).getIdfRoom());
		for(RoomImageDto item:wrapperDto.getRoomImage()){
			RoomImage image=new RoomImage();
			BeanUtils.copyProperties(item, image);
			image.setRoom(room);
			roomImageDao.update(image);
		}
		jsonDto.setResultCode("S");
		return jsonDto;
	}

	@Override
	public JsonDto<RoomImage> roomImageListDelete(WrapperDto wrapperDto) {
		// TODO Auto-generated method stub
		JsonDto<RoomImage> jsonDto=new JsonDto<RoomImage>();
		for(RoomImageDto item:wrapperDto.getRoomImage()){
			roomImageDao.delete(roomImageDao.readById(item.getIdfRoomImage()));
			imageUtil.deleteImage("room-image.pensionrun.com", item.getIdfRoom()+"/"+item.getImage());
		}
		jsonDto.setResultCode("S");
		return jsonDto;
	}

	@Override
	public JsonDto<RoomImageDto> readRoomImageByIdfRoom(Integer idfRoom) {
		// TODO Auto-generated method stub
		JsonDto<RoomImageDto> jsonDto=new JsonDto<RoomImageDto>();
		jsonDto.setDataList(new ArrayList<RoomImageDto>());
		for(RoomImage image : roomImageDao.readRoomImageByIdfRoom(idfRoom)){
			RoomImageDto item=new RoomImageDto();
			item.setPriority(image.getPriority());
			item.setIdfRoomImage(image.getIdfRoomImage());
			item.setImage(image.getImage());
			item.setIdfRoom(image.getRoom().getIdfRoom());
			jsonDto.getDataList().add(item);
			
		}
		jsonDto.setResultCode("S");
		return jsonDto;
	}




}
