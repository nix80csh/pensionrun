package com.pensionrun.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.AppValidationDto;
import com.pensionrun.dto.AuthAppDto;
import com.pensionrun.dto.JsonDto;

@Transactional
public interface AuthService {
	JsonDto<AppValidationDto> sendSMS(AppValidationDto _appValidationDto);

	JsonDto<AppValidationDto> validateSMSCode(AppValidationDto _appValidationDto);

	JsonDto<AppValidationDto> validateEmail(AppValidationDto _appValidationDto);

	JsonDto<AuthAppDto> prAuth(String userid, String password, HttpServletResponse res);

	JsonDto<AuthAppDto> snsAuth(String accessToken, String accessTokenType, HttpServletResponse res);

}
