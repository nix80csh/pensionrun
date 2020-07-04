package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.NoticeDao;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.NoticeDto;
import com.pensionrun.entity.Admin;
import com.pensionrun.entity.Notice;
@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeDao noticeDao;

	@Override
	public JsonDto<NoticeDto> noticeCreate(NoticeDto _notice) {
		// TODO Auto-generated method stub
		JsonDto<NoticeDto> jDto=new JsonDto<NoticeDto>();
		Notice notice=new Notice();
		Admin admin=new Admin();
		admin.setIdfAdmin(_notice.getIdfAdmin());
		BeanUtils.copyProperties(_notice, notice);
		notice.setAdmin(admin);
		_notice.setIdfNotice(noticeDao.create(notice).getIdfNotice());
		jDto.setDataObject(_notice);
		jDto.setResultCode("S");
		return jDto;
	}

	@Override
	public JsonDto<NoticeDto> noticeUpdate(NoticeDto _notice) {
		// TODO Auto-generated method stub
		JsonDto<NoticeDto> jDto=new JsonDto<NoticeDto>();
		Notice notice=new Notice();
		BeanUtils.copyProperties(_notice, notice);
		Admin admin=new Admin();
		admin.setIdfAdmin(_notice.getIdfAdmin());
		notice.setAdmin(admin);
		noticeDao.update(notice);
		jDto.setResultCode("S");
		return jDto;
	}

	@Override
	public JsonDto<NoticeDto> noticeDelete(NoticeDto _notice) {
		// TODO Auto-generated method stub
		JsonDto<NoticeDto> jDto=new JsonDto<NoticeDto>();
		Notice notice=noticeDao.readById(_notice.getIdfNotice());
		if (notice == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exsit Notice");
		} else {
			noticeDao.delete(notice);
			jDto.setResultCode("S");
		}
		return jDto;
	}

	@Override
	public JsonDto<NoticeDto> readNoticeByIdfNotice(Integer idfNotice) {
		// TODO Auto-generated method stub
		JsonDto<NoticeDto> jDto=new JsonDto<NoticeDto>();
		NoticeDto noticeDto=new NoticeDto();
		Notice notice=noticeDao.readById(idfNotice);
		if (notice == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Not Exsit Notice");
		} else {
			BeanUtils.copyProperties(notice,noticeDto);
			noticeDto.setIdfAdmin(notice.getAdmin().getIdfAdmin());
			jDto.setDataObject(noticeDto);
			jDto.setResultCode("S");			
		}
		return jDto;
	}

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
