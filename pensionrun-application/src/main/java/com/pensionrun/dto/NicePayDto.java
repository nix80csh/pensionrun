package com.pensionrun.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NicePayDto {
	private String payMethod;		// 지불 수단
	private String moid;				// 주문 번호
	private String TID;				// 거래 아이디
	private String amt;				// 상품 가격
	private String goodsName;		// 상품명
	private String buyerName;		// 구매자 이름
	private String buyerTel;			// 구매자 연락처
	private String buyerEmail;		// 구매자 이메일
	private String resultCode;		// 결과코드
	private String resultMsg        ;		// 결과 메시지
	private String dstAddr			;			// 휴대폰 번호(휴대폰 결제시)
	private String mallReserved;
	private Date regDate;
	private String vbankExpDate;//가상계좌 입급 만료 날
	private String vbankBankName; //가상계좌 은행 이
	private String vbankNum;
	private String authDate;
	private String fnCd;
	private String vbankInputName;
	private String rcptTID;
	private String rcptType;
	private String rcptAuthCode;
	private String cardNo;
	private String cardCode;
	private String cardName;
	private String cardQuota;
	private String authCode;
	
}
