package com.pensionrun.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pensionrun.entity.AppInfo;


@Repository
public class AppInfoDaoImpl extends GenericDaoImpl<AppInfo> implements AppInfoDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AppInfo> readByVersion(String version, char typeOs) {
		Query q = em.createQuery("from AppInfo ai where ai.id.version > :version and ai.id.typeOs = :typeOs", AppInfo.class);		
		 q.setParameter("version", version);
		 q.setParameter("typeOs", typeOs);		 
		 return q.getResultList();
	}
	
	
}
