package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionDto;
import com.pensionrun.dto.PensionInquiryDto;
import com.pensionrun.dto.PensionListViewDto;

@Transactional
public interface PensionService {

	JsonDto<PensionDto> readPensionByIdfPension(Integer _idfPension);
	JsonDto<PensionListViewDto> readPensionListViewByIdfRecommendation(Integer _idfRecommendation, Integer _idfAccount);
	JsonDto<PensionInquiryDto> pensionInquiryCreate(PensionInquiryDto pensionInquiryDto);
}
