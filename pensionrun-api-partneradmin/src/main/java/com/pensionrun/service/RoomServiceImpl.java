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
