package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionDto;
import com.pensionrun.dto.PensionInquiryDto;
import com.pensionrun.dto.PensionListViewDto;
import com.pensionrun.service.PensionService;

@Controller
@RequestMapping(value = "/pension")
public class PensionController {

	@Autowired
	PensionService pensionService;

	@ResponseBody
	@RequestMapping(value = "/readPensionByIdfPension/{idfPension}", method = RequestMethod.GET)
	public ResponseEntity<Object> readPensionByIdfPension(@PathVariable Integer idfPension) {
		JsonDto<PensionDto> jDto = pensionService.readPensionByIdfPension(idfPension);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
    @RequestMapping(value = "/readPensionListViewByIdfRecommendation/{idfRecommendation}", method = RequestMethod.GET)
    public ResponseEntity<Object> readPensionListViewByIdfRecommendation(@PathVariable Integer idfRecommendation) {        
        JsonDto<PensionListViewDto> jDto = pensionService.readPensionListViewByIdfRecommendation(idfRecommendation, null);
        return new ResponseEntity<Object>(jDto, HttpStatus.OK);
    }
	
	@ResponseBody
    @RequestMapping(value = "/readPensionListViewByIdfRecommendation/{idfRecommendation}/{idfAccount}", method = RequestMethod.GET)
    public ResponseEntity<Object> readPensionListViewByIdfRecommendation(@PathVariable Integer idfRecommendation, @PathVariable Integer idfAccount) {        
        JsonDto<PensionListViewDto> jDto = pensionService.readPensionListViewByIdfRecommendation(idfRecommendation, idfAccount);
        return new ResponseEntity<Object>(jDto, HttpStatus.OK);
    }
	
	@ResponseBody
    @RequestMapping(value = "/pensionInquiryCreate", method = RequestMethod.POST)
    public ResponseEntity<Object> pensionInquiryCreate(@RequestBody PensionInquiryDto pensionInquiryDto) {        
        JsonDto<PensionInquiryDto> jDto = pensionService.pensionInquiryCreate(pensionInquiryDto);
        return new ResponseEntity<Object>(jDto, HttpStatus.OK);
    }
	
	
}
