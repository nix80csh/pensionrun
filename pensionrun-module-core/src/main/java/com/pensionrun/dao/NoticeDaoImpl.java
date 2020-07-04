package com.pensionrun.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.Notice;
@Repository
public class NoticeDaoImpl extends GenericDaoImpl<Notice> implements NoticeDao {
	@PersistenceContext
	private EntityManager em;

}
