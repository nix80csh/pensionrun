package com.pensionrun.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pensionrun.dao.AdminDao;
import com.pensionrun.dto.AuthAdminDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.entity.Admin;
import com.pensionrun.util.TokenUtil;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AdminDao adminDao;

	@Autowired	
	private AuthenticationManager authenticationManager;

	@Override
	public JsonDto<AuthAdminDto> auth(String email, String password, HttpServletResponse res) {
		JsonDto<AuthAdminDto> jDto = new JsonDto<AuthAdminDto>();
		try {			
			// 인증
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
			Authentication authentication = this.authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// 토큰생성
			Admin admin = adminDao.readByEmail(email);
			String xAuthToken = TokenUtil.createToken(admin.getEmail(), admin.getPassword());
			//System.out.println(xAuthToken);
			// 쿠키생성
			Cookie cookie = new Cookie("X-Auth-Token", xAuthToken);			
			res.addCookie(cookie);		

			AuthAdminDto dto = new AuthAdminDto();
			dto.setIdfAdmin(admin.getIdfAdmin());	
			dto.setXAuthToken(xAuthToken);
			// json format설정
			jDto.setResultCode("S");
			jDto.setDataObject(dto);
		} catch (BadCredentialsException e) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Invalid Email or Password");
		}
		return jDto;
	}

}
