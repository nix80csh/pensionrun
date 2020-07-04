package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.NoticeDao;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.NoticeDto;
import com.pensionrun.entity.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public JsonDto<NoticeDto> noticeList() {
		// TODO Auto-generated method stub
		JsonDto<NoticeDto> jDto=new JsonDto<NoticeDto>();
		List<NoticeDto> noticeDtoList=new ArrayList<NoticeDto>();
		List<Notice> noticeList=noticeDao.readAll();
		if (noticeList.size() == 0) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exsit PensionProvide");
		} else {
			for(Notice n:noticeDao.readAll()){
				NoticeDto notice=new NoticeDto();
				BeanUtils.copyProperties(n, notice);
				notice.setIdfAdmin(n.getAdmin().getIdfAdmin());
				noticeDtoList.add(notice);
			}
			jDto.setDataList(noticeDtoList);
			jDto.setResultCode("S");
		}
		return jDto;
	}
}
