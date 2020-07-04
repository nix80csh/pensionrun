package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.AccountPensionDao;
import com.pensionrun.dao.PensionDao;
import com.pensionrun.dao.PensionImageDao;
import com.pensionrun.dao.PensionInquiryDao;
import com.pensionrun.dao.RecommendationPensionDao;
import com.pensionrun.dao.RoomDao;
import com.pensionrun.dao.RoomPriceDao;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionDto;
import com.pensionrun.dto.PensionInquiryDto;
import com.pensionrun.dto.PensionListViewDto;
import com.pensionrun.entity.AccountPensionId;
import com.pensionrun.entity.Pension;
import com.pensionrun.entity.PensionImage;
import com.pensionrun.entity.PensionInquiry;
import com.pensionrun.entity.RecommendationPension;
import com.pensionrun.entity.RoomPrice;

@Service
public class PensionServiceImpl implements PensionService {

	@Autowired
	PensionDao pensionDao;
	@Autowired
	RoomDao roomDao;
	@Autowired
	RoomPriceDao roomPriceDao;
	@Autowired
	RecommendationPensionDao recommendationPensionDao;
	@Autowired
	PensionImageDao pensionImageDao;
	@Autowired
	AccountPensionDao accountPensionDao;
	@Autowired
	PensionInquiryDao pensionInquiryDao;

	@Override
	public JsonDto<PensionDto> readPensionByIdfPension(Integer _idfPension) {
		JsonDto<PensionDto> jDto = new JsonDto<PensionDto>();
		PensionDto pensionDto = new PensionDto();
		Pension pension = pensionDao.readById(_idfPension);
		if (pension == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exist Pension");
		} else {
			pensionDto.setIdfArea2(pension.getArea2().getIdfArea2());
			pensionDto.setRoomCount(roomDao.countByIdfPension(pension.getIdfPension()));
			BeanUtils.copyProperties(pension, pensionDto);

			jDto.setResultCode("S");
			jDto.setDataObject(pensionDto);
		}
		return jDto;
	}

	@Override
	public JsonDto<PensionListViewDto> readPensionListViewByIdfRecommendation(Integer _idfRecommendation,
			Integer _idfAccount) {
		JsonDto<PensionListViewDto> jDto = new JsonDto<PensionListViewDto>();
		List<PensionListViewDto> pensionListViewDtoList = new ArrayList<PensionListViewDto>();
		List<RecommendationPension> recommendationPensionList = recommendationPensionDao
				.readByIdfRecommendation(_idfRecommendation);

		for (RecommendationPension rp : recommendationPensionList) {
			PensionListViewDto pensionListViewDto = new PensionListViewDto();

			RoomPrice roomPrice = roomPriceDao.readTodayPriceByIdfPension(rp.getId().getIdfPension());
			if (roomPrice == null) {

				PensionImage pensionImage = pensionImageDao.readMainImageByIdfPension(rp.getId().getIdfPension());
				if (pensionImage != null) {
					pensionListViewDto.setImage(pensionImage.getImage());
				}

				pensionListViewDto.setIdfPension(rp.getId().getIdfPension());
				pensionListViewDto.setAddr1(rp.getPension().getAddr1());
				pensionListViewDto.setName(rp.getPension().getName());
				pensionListViewDto.setPrice(roomPrice.getPrice());
				pensionListViewDto.setPriceDiscount(roomPrice.getPriceDiscount());
				if (_idfAccount != null) {
					AccountPensionId accountPensionId = new AccountPensionId();
					accountPensionId.setIdfAccount(_idfAccount);
					accountPensionId.setIdfPension(rp.getId().getIdfPension());
					if (accountPensionDao.readById(accountPensionId) == null) {
						pensionListViewDto.setZzimState("off");
					} else {
						pensionListViewDto.setZzimState("on");
					}
				}
				pensionListViewDtoList.add(pensionListViewDto);
			}
		}
		jDto.setResultCode("S");
		jDto.setDataList(pensionListViewDtoList);
		return jDto;
	}

	@Override
	public JsonDto<PensionInquiryDto> pensionInquiryCreate(PensionInquiryDto pensionInquiryDto) {
		JsonDto<PensionInquiryDto> jDto = new JsonDto<PensionInquiryDto>();
		PensionInquiry pensionInquiry = new PensionInquiry();
		BeanUtils.copyProperties(pensionInquiryDto, pensionInquiry);
		pensionInquiryDao.create(pensionInquiry);
		jDto.setResultCode("S");
		return jDto;
	}

}
