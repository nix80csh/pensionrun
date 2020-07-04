package com.pensionrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.service.NoticeService;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("noticeList")
	@ResponseBody
	public ResponseEntity<Object> noticeList(){		
		System.out.println(noticeService.noticeList());
		return  new ResponseEntity<Object>(noticeService.noticeList(), HttpStatus.OK);
	}	

}
