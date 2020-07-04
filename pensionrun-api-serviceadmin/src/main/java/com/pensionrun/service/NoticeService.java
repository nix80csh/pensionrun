package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.NoticeDto;
import com.pensionrun.entity.Notice;
@Transactional
public interface NoticeService{
	JsonDto<NoticeDto> noticeCreate(NoticeDto _notice);	
	JsonDto<NoticeDto> noticeUpdate(NoticeDto _notice);	
	JsonDto<NoticeDto> noticeDelete(NoticeDto _notice);
	

	JsonDto<NoticeDto> readNoticeByIdfNotice(Integer idfNotice);
	
	@Transactional(readOnly=true)
	JsonDto<NoticeDto> noticeList();
}
