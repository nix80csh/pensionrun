package com.pensionrun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pensionrun.dto.NoticeDto;
import com.pensionrun.entity.Notice;
import com.pensionrun.service.NoticeService;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	@Autowired
	private NoticeService noticeService;
	@RequestMapping(value="noticeCreate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> noticeCreate(@RequestBody NoticeDto notice){
		System.out.println(notice.toString());
		return new ResponseEntity<Object>(noticeService.noticeCreate(notice), HttpStatus.OK);
	}
	@RequestMapping(value="noticeUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> noticeUpdate(@RequestBody NoticeDto notice){
		return  new ResponseEntity<Object>(noticeService.noticeUpdate(notice), HttpStatus.OK);
	}
	@RequestMapping(value="noticeDelete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> noticeDelete(@RequestBody NoticeDto notice){
		return  new ResponseEntity<Object>(noticeService.noticeDelete(notice), HttpStatus.OK);
	}
	
	@RequestMapping(value="readNoticeByIdfNotice/{idfNotice}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> readNoticeByIdfNotice(@PathVariable("idfNotice") Integer idfNotice){
		return  new ResponseEntity<Object>(noticeService.readNoticeByIdfNotice(idfNotice), HttpStatus.OK);
	}

	@RequestMapping(value="noticeList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> noticeList(){
		return  new ResponseEntity<Object>(noticeService.noticeList(), HttpStatus.OK);
	}
}
