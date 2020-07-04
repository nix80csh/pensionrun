package com.pensionrun.service;




import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.pensionrun.dto.AuthAdminDto;
import com.pensionrun.dto.JsonDto;

@Transactional
public interface AuthService {
	 
	JsonDto<AuthAdminDto> auth(String email, String password, HttpServletResponse res);
	
}
