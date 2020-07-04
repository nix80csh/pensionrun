package com.pensionrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.AppInfoDao;
import com.pensionrun.dto.AppInfoDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.entity.AppInfo;

@Service
public class AppInfoServiceImpl implements AppInfoService{

	@Autowired
	AppInfoDao appInfoDao;
	
	@Override
	public JsonDto<AppInfoDto> readAppInfoListByVersion(String version, char typeOs) {
		
		JsonDto<AppInfoDto> jDto = new JsonDto<AppInfoDto>();
		List<AppInfoDto> appInfoDtoList = new ArrayList<AppInfoDto>();
		
		List<AppInfo> appInfoList = appInfoDao.readByVersion(version, typeOs);
		for (AppInfo ai : appInfoList) {
			AppInfoDto appInfoDto = new AppInfoDto();			
			BeanUtils.copyProperties(ai, appInfoDto);
			appInfoDto.setVersion(ai.getId().getVersion());
			appInfoDto.setTypeOs(ai.getId().getTypeOs());
			appInfoDtoList.add(appInfoDto);
		}
		jDto.setResultCode("S");
		jDto.setDataList(appInfoDtoList);		
		return jDto;
	}

}
