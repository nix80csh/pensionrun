package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.Notice;

public interface NoticeDao extends GenericDao<Notice> {
	List<Notice> readAll(); 
}
