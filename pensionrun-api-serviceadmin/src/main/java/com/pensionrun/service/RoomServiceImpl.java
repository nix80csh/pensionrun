package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.AmenityDao;
import com.pensionrun.dao.RoomAmenityDao;
import com.pensionrun.dao.RoomDao;
import com.pensionrun.dao.RoomPriceDao;
import com.pensionrun.dto.AmenityDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.RoomAmenityDto;
import com.pensionrun.dto.RoomDto;
import com.pensionrun.dto.RoomPriceDto;
import com.pensionrun.dto.WrapperDto;
import com.pensionrun.entity.Amenity;
import com.pensionrun.entity.Pension;
import com.pensionrun.entity.Room;
import com.pensionrun.entity.RoomAmenity;
import com.pensionrun.entity.RoomAmenityId;
import com.pensionrun.entity.RoomPrice;
import com.pensionrun.entity.RoomPriceId;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomDao roomDao;
	@Autowired
	RoomPriceDao roomPriceDao;

	@Autowired
	RoomAmenityDao roomAmenityDao;
	@Autowired
	AmenityDao amenityDao;

	@Override
	public JsonDto<RoomDto> roomCreate(RoomDto _roomDto) {
		JsonDto<RoomDto> jDto = new JsonDto<RoomDto>();
		Room room = new Room();
		Pension pension = new Pension();
		pension.setIdfPension(_roomDto.getIdfPension());
		room.setPension(pension);
		BeanUtils.copyProperties(_roomDto, room);
		roomDao.create(room);
		_roomDto.setIdfRoom(room.getIdfRoom());

		jDto.setResultCode("S");
		jDto.setDataObject(_roomDto);
		return jDto;
	}

	@Override
	public JsonDto<RoomDto> roomUpdate(RoomDto _roomDto) {
		JsonDto<RoomDto> jDto = new JsonDto<RoomDto>();
		Room room = new Room();
		Pension pension = new Pension();
		pension.setIdfPension(_roomDto.getIdfPension());
		room.setPension(pension);
		BeanUtils.copyProperties(_roomDto, room);
		roomDao.update(room);

		jDto.setResultCode("S");
		// jDto.setDataObject(_pensionDto);
		return jDto;
	}

	@Override
	public JsonDto<RoomDto> roomPriorityUpdate(WrapperDto _wrapperDto) {
		JsonDto<RoomDto> jDto = new JsonDto<RoomDto>();
		List<RoomDto> roomDtoList = _wrapperDto.getRoom();

		for (RoomDto r : roomDtoList) {
			Room room = roomDao.readById(r.getIdfRoom());
			room.setPriority(r.getPriority());
			roomDao.update(room);
		}
		jDto.setResultCode("S");
		// jDto.setDataObject(_pensionDto);
		return jDto;
	}

	@Override
	public JsonDto<RoomDto> roomDelete(RoomDto _roomDto) {
		JsonDto<RoomDto> jDto = new JsonDto<RoomDto>();
		Room room = roomDao.readById(_roomDto.getIdfRoom());

		if (room == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist Pension Data");
		} else {
			roomDao.delete(room);
			jDto.setResultCode("S");
		}
		return jDto;
	}

	@Override
	public JsonDto<RoomAmenityDto> roomAmenityCreate(WrapperDto _wrapperDto) {
		JsonDto<RoomAmenityDto> jDto = new JsonDto<RoomAmenityDto>();
		List<RoomAmenityDto> roomAmenityDtoList = _wrapperDto.getRoomAmenity();

		// 삭제
		roomAmenityDao.deleteByIdfRoom(roomAmenityDtoList.get(0).getIdfRoom());

		// 등록
		for (RoomAmenityDto rDto : roomAmenityDtoList) {
			RoomAmenity roomAmenity = new RoomAmenity();
			RoomAmenityId roomAmenityId = new RoomAmenityId();

			roomAmenityId.setIdfRoom(rDto.getIdfRoom());
			roomAmenityId.setIdfAmenity(rDto.getIdfAmenity());
			roomAmenity.setId(roomAmenityId);
			roomAmenityDao.create(roomAmenity);
		}

		jDto.setResultCode("S");
		jDto.setDataList(roomAmenityDtoList);
		return jDto;
	}

	@Override
	public JsonDto<RoomPriceDto> roomPriceCreate(WrapperDto _wrapperDto) {
		JsonDto<RoomPriceDto> jDto = new JsonDto<RoomPriceDto>();
		List<RoomPriceDto> roomPriceDtoList = _wrapperDto.getRoomPrice();
		for (RoomPriceDto rp : roomPriceDtoList) {
			RoomPriceId roomPriceId = new RoomPriceId();
			RoomPrice roomPrice = new RoomPrice();
			roomPriceId.setIdfRoom(rp.getIdfRoom());
			roomPriceId.setCheckDate(rp.getCheckDate());
			if (roomPriceDao.readById(roomPriceId) == null) {
				BeanUtils.copyProperties(rp, roomPrice);
				roomPrice.setId(roomPriceId);
				roomPriceDao.create(roomPrice);
			} else {
				roomPriceDao.delete(roomPriceDao.readById(roomPriceId));
				BeanUtils.copyProperties(rp, roomPrice);
				roomPrice.setId(roomPriceId);
				roomPriceDao.create(roomPrice);
			}
		}
		jDto.setResultCode("S");
		return jDto;
	}

	@Override
	public JsonDto<RoomDto> readRoomByIdfPension(Integer _idfPension) {
		JsonDto<RoomDto> jDto = new JsonDto<RoomDto>();
		List<RoomDto> roomDtoList = new ArrayList<RoomDto>();
		List<Room> roomList = roomDao.readByIdfPension(_idfPension);

		for (Room r : roomList) {
			RoomDto roomDto = new RoomDto();
			BeanUtils.copyProperties(r, roomDto);
			roomDto.setIdfPension(r.getPension().getIdfPension());
			roomDtoList.add(roomDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(roomDtoList);
		return jDto;
	}

	@Override
	public JsonDto<RoomPriceDto> readRoomPriceListByCheckDate(RoomPriceDto _roomPriceDto) {
		JsonDto<RoomPriceDto> jDto = new JsonDto<RoomPriceDto>();
		 List<RoomPriceDto> roomPriceDtoList = new ArrayList<RoomPriceDto>();
		List<RoomPrice> roomPriceList = roomPriceDao.readByCheckDate(_roomPriceDto.getIdfRoom(),
				_roomPriceDto.getStartCheckDate(), _roomPriceDto.getEndCheckDate());

		for (RoomPrice rp : roomPriceList) {
			RoomPriceDto roomPriceDto = new RoomPriceDto();
			
			BeanUtils.copyProperties(rp, roomPriceDto);
			roomPriceDto.setIdfRoom(rp.getId().getIdfRoom());
			roomPriceDto.setCheckDate(rp.getId().getCheckDate());			
			roomPriceDtoList.add(roomPriceDto);
		}
		
		jDto.setResultCode("S");
		jDto.setDataList(roomPriceDtoList);
		return jDto;
	}

	@Override
	public JsonDto<RoomAmenityDto> readAmenityByIdfRoom(Integer _idfRoom) {
		JsonDto<RoomAmenityDto> jDto = new JsonDto<RoomAmenityDto>();
		List<RoomAmenityDto> roomAmenityDtoList = new ArrayList<RoomAmenityDto>();
		List<RoomAmenity> roomAmenityList = roomAmenityDao.readByIdfRoom(_idfRoom);

		if (roomAmenityList.size() == 0) {

			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist PensionAmenity");
		} else {

			for (RoomAmenity ra : roomAmenityList) {
				RoomAmenityDto roomAmenityDto = new RoomAmenityDto();
				roomAmenityDto.setIdfRoom(ra.getRoom().getIdfRoom());
				roomAmenityDto.setIdfAmenity(ra.getAmenity().getIdfAmenity());
				roomAmenityDto.setName(ra.getAmenity().getName());
				roomAmenityDtoList.add(roomAmenityDto);
			}

			jDto.setResultCode("S");
			jDto.setDataList(roomAmenityDtoList);
		}
		return jDto;

	}

	@Override
	public JsonDto<AmenityDto> amenityList() {
		JsonDto<AmenityDto> jDto = new JsonDto<AmenityDto>();
		List<AmenityDto> amenityDtoList = new ArrayList<AmenityDto>();
		List<Amenity> amenityList = amenityDao.readAll();

		for (Amenity a : amenityList) {
			AmenityDto amenityDto = new AmenityDto();

			BeanUtils.copyProperties(a, amenityDto);
			amenityDtoList.add(amenityDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(amenityDtoList);

		return jDto;
	}

}
