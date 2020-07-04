package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.NoticeDto;

@Transactional
public interface NoticeService {
	@Transactional(readOnly=true)
	JsonDto<NoticeDto> noticeList();
}
