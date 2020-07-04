package com.pensionrun.service;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.AppInfoDto;
import com.pensionrun.dto.JsonDto;

@Transactional
public interface AppInfoService {

	@Transactional(readOnly=true)
	JsonDto<AppInfoDto> readAppInfoListByVersion(String version, char typeOs);
}
