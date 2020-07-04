package com.pensionrun.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pensionrun.dao.AccountDao;
import com.pensionrun.dto.AppValidationDto;
import com.pensionrun.dto.AuthAppDto;
import com.pensionrun.dto.JsonDto;
import com.pensionrun.dto.SNSDto;
import com.pensionrun.entity.Account;
import com.pensionrun.util.Coolsms;
import com.pensionrun.util.EmailValidUtil;
import com.pensionrun.util.EncryptionSha256Util;
import com.pensionrun.util.TokenUtil;

@Service
public class AuthServiceImpl implements AuthService  {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public JsonDto<AuthAppDto> prAuth(String userid, String password, HttpServletResponse res) {
		JsonDto<AuthAppDto> jDto = new JsonDto<AuthAppDto>();
		String encPassword = EncryptionSha256Util.getEncSHA256(password);
		try {
			// 인증
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userid,
					encPassword);
			Authentication authentication = this.authenticationManager.authenticate(authToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// 토큰생성
			Account account = accountDao.readByUserId(userid);
			String xAuthToken = TokenUtil.createToken(account.getUserid(), account.getPassword());
			// System.out.println(xAuthToken);
			// 쿠키생성
			Cookie cookie = new Cookie("X-Auth-Token", xAuthToken);
			res.addCookie(cookie);

			AuthAppDto authAppDto = new AuthAppDto();
			authAppDto.setIdfAccount(account.getIdfAccount());

			// json format설정

			jDto.setResultCode("S");
			jDto.setDataObject(authAppDto);
		} catch (BadCredentialsException e) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Invalid UserId or Password");
		}
		return jDto;
	}

	@Override
	public JsonDto<AuthAppDto> snsAuth(String accessToken, String accessTokenType, HttpServletResponse res) {
		SNSDto snsDto = new SNSDto();
		JsonDto<AuthAppDto> jDto = new JsonDto<AuthAppDto>();
		snsDto = connectSNSApiForId(accessToken, accessTokenType);
		
		if (snsDto == null) {
			jDto.setResultCode("F");
			jDto.setResultMessage("Invaild accessToken");
		} else {			
			String userid = snsDto.getId();
			String encPassword = EncryptionSha256Util.getEncSHA256(userid);
			try {
				// 인증
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userid,
						encPassword);
				Authentication authentication = this.authenticationManager.authenticate(authToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);

				// 토큰생성
				Account account = accountDao.readByUserId(userid);
				String xAuthToken = TokenUtil.createToken(account.getUserid(), account.getPassword());
				// System.out.println(xAuthToken);
				// 쿠키생성
				Cookie cookie = new Cookie("X-Auth-Token", xAuthToken);
				res.addCookie(cookie);

				AuthAppDto authAppDto = new AuthAppDto();
				authAppDto.setIdfAccount(account.getIdfAccount());

				// json format설정
				jDto.setResultCode("S");
				jDto.setDataObject(authAppDto);
			} catch (BadCredentialsException e) {
				jDto.setResultCode("F");
				jDto.setResultMessage("Invalid UserId or Password");
			}
		}
		return jDto;
	}

	@Override
	public JsonDto<AppValidationDto> sendSMS(AppValidationDto _appValidationDto) {
		JsonDto<AppValidationDto> jDto = new JsonDto<AppValidationDto>();
		Coolsms coolsms = new Coolsms();
		Random random = new Random();
		int smsCode = random.nextInt(10000) + 1000;
		if (smsCode > 10000) {
			smsCode = smsCode - 1000;
		}
		String SMSMessage = "[" + Integer.toString(smsCode) + "] 인증번호를 입력하세요.";

		HashMap<String, String> set = new HashMap<String, String>();
		set.put("to", _appValidationDto.getMobile()); // 수신번호
		set.put("from", "0216884501"); // 발신번호
		set.put("text", SMSMessage); // 문자내용
		set.put("type", "sms"); // 문자 타입

		JSONObject result = coolsms.send(set); // 보내기&전송결과받기
		if ((Boolean) result.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			_appValidationDto.setMessageId(result.get("group_id").toString());
			jDto.setResultCode("S");
			jDto.setDataObject(_appValidationDto);
			// jDto.setResultMessage("");
		} else {
			jDto.setResultCode("F");
			jDto.setResultMessage(result.get("message").toString());
		}
		return jDto;
	}

	@Override
	public JsonDto<AppValidationDto> validateSMSCode(AppValidationDto _appValidationDto) {
		JsonDto<AppValidationDto> jDto = new JsonDto<AppValidationDto>();
		Coolsms coolsms = new Coolsms();
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("gid", _appValidationDto.getMessageId()); // group_id
		set.put("s_rcpt", _appValidationDto.getMobile()); // 수신번호

		JSONObject result = coolsms.sent(set);
		if ((Boolean) result.get("status") == true) {
			JSONArray data = (JSONArray) result.get("data");
			JSONObject obj = (JSONObject) data.get(0);
			String SMSCode = obj.get("text").toString();
			// System.out.println("조회 : "+SMSCode.substring(1, 5));
			// System.out.println("입력 : "+_smsDto.getSmsCode());
			if (SMSCode.substring(1, 5).equals(_appValidationDto.getSmsCode())) {
				jDto.setResultCode("S");
			} else {
				jDto.setResultCode("F");
				jDto.setResultMessage("invalid SMSCode");
			}
		} else {
			jDto.setResultCode("F");
			jDto.setResultMessage("sms system connection failed");
		}
		return jDto;
	}

	@Override
	public JsonDto<AppValidationDto> validateEmail(AppValidationDto _appValidationDto) {
		JsonDto<AppValidationDto> jDto = new JsonDto<AppValidationDto>();
		jDto.setResultCode(EmailValidUtil.checkValid(_appValidationDto.getEmail())); 
		return jDto;
	}	

	@SuppressWarnings("rawtypes")
	private SNSDto connectSNSApiForId(String accessToken, String accessTokenType) {

		SNSDto snsDto = new SNSDto();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			if (accessTokenType.equals("F")) {
				String snsUrl = "https://graph.facebook.com/v2.6/me?access_token=" + accessToken;
				snsDto = restTemplate.getForObject(snsUrl, SNSDto.class);
				System.out.println(snsDto.getId());
			} else if (accessTokenType.equals("K")) {
				String snsUrl = "https://kapi.kakao.com/v1/user/me";
				headers.add("Authorization", "Bearer " + accessToken);
				HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
				
				ResponseEntity<Object> resEntityobj = restTemplate.exchange(snsUrl, HttpMethod.GET, httpEntity,
						Object.class);
				
				String jsonString = new JSONObject((Map) resEntityobj.getBody()).toString();
				ObjectMapper mapper = new ObjectMapper();

				com.fasterxml.jackson.databind.JsonNode jsonObj = mapper.readTree(jsonString);
				snsDto.setId(jsonObj.get("id").toString());

			}else{
				snsDto = null;
			}

		} catch (Exception e) {
			snsDto = null;
		}
		return snsDto;
	}

}
