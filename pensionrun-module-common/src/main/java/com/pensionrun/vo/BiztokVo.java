package com.pensionrun.vo;

import lombok.Data;

@Data
public class BiztokVo {

	private String userName;// 유저이름
	private String phone;// 전화번호
	private String bookingNumber;// 펜튀 예약번
	private String inwon;// 인원
	private String price;// 가격
	private String totalDay;// 이용일 및 기간 ex)2016년 3월 30일(수) 1박
	private String pensionName;// 펜션 이름
	private String roomName;// 펜션 날짜
	private String sendType;// 카톡 템플릿 코드
	private String accountNumber;// 입금 정보 ex)**은행 1231-12312-123 (주)펜션으로 튀어라
	private String accountNumberTime;// 입금 대기 시간 ex)****년**월**일**시**분까지


}
