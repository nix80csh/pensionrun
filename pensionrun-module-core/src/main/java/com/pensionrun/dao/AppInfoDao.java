package com.pensionrun.dao;

import java.util.List;

import com.pensionrun.entity.AppInfo;

public interface AppInfoDao extends GenericDao<AppInfo>{
	
	List<AppInfo> readByVersion(String version, char typeOs);
	
}
