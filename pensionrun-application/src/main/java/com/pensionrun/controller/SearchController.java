package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.PensionCountPerAreaListDto;
import com.pensionrun.dto.SearchEngineDto;
import com.pensionrun.service.SearchService;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	SearchService searchService;
	
	@ResponseBody
	@RequestMapping(value = "/pensionCountPerAreaList", method = RequestMethod.GET)
	public ResponseEntity<Object> pensionCountPerAreaList() {		
		JsonDto<PensionCountPerAreaListDto> jDto = searchService.pensionCountPerAreaList();
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchEngine/{searchName}", method = RequestMethod.GET)
	public ResponseEntity<Object> searchEngine(@PathVariable String searchName) {		
		JsonDto<SearchEngineDto> jDto = searchService.searchEngineList(searchName);
		return new ResponseEntity<Object>(jDto, HttpStatus.OK);
	}
	
}
