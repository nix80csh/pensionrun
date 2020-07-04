package com.pensionrun.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.co.nicepay.module.lite.NicePayWebConnector;


public class NicePayUtil {
	public static String MERCHANT_KEY_1 = "SZmlgLo34nBTSjDX623WfkSz2pyB89f5yZxrWw7xSqqF73AfbTnpMqomRbyShu+wmAd666TTzrPZoj7SKbnfEw==";
	public static String MERCHANT_ID_1= "pensiond1m";
	public static String MERCHANT_KEY_2 = "4ccVtwHpKv5UeNCPYd8Es59O/zwcRJ8+LFzzkErLAqQ/lVx86sRhxTTn4HvckxMPDy3PzWo1TH2pNSc4xYllGw==";
	public static String MERCHANT_ID_2= "pensiond2m";
	// 상점 MID
	public static String nicePayLogPath="/var/log/nicepay";

	public  static String encrypt(String strData) { // 암호화 시킬 데이터
		String strOUTData = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // "MD5 형식으로 암호화"
			md.reset();
			//byte[] bytData = strData.getBytes();  
			//md.update(bytData);
			md.update(strData.getBytes());
			byte[] digest = md.digest();

			StringBuffer hashedpasswd = new StringBuffer();
			String hx;

			for (int i=0;i<digest.length;i++){
				hx =  Integer.toHexString(0xFF & digest[i]);
				//0x03 is equal to 0x3, but we need 0x03 for our md5sum
				if(hx.length() == 1){hx = "0" + hx;}
				hashedpasswd.append(hx);

			}
			strOUTData = hashedpasswd.toString();
			byte[] raw = strOUTData.getBytes(); 
			byte[] encodedBytes = Base64.encodeBase64(raw);
			strOUTData = new String(encodedBytes);
		}
		catch (NoSuchAlgorithmException e) {
			System.out.print("암호화 에러" + e.toString());
		}


		return strOUTData;  // 암호화된 데이터를 리턴...
	}
	public static String getVbankExpDate(int minute) throws Exception{
		Timestamp toDay = new Timestamp((new Date()).getTime());
		Timestamp nxDay = NicePayUtil.getTimestampWithSpan(toDay, minute);
		String VbankExpDate = nxDay.toString();
		VbankExpDate = VbankExpDate.substring(0, 17); 
		VbankExpDate = VbankExpDate.replaceAll("-", "").replaceAll(":", "").replaceAll(" ","");
		return VbankExpDate;

	}

	public static String getHashString(String date,String merchantKey,String merchantID,String price){
		return NicePayUtil.encrypt(date+merchantKey+price+merchantID);
	}
	public static Timestamp getTimestampWithSpan(Timestamp sourceTS,int minute) throws Exception {
		Timestamp targetTS = null;
		if (sourceTS != null) {
			//가상계좌 대기 시간
			targetTS = new Timestamp(sourceTS.getTime() + (1000 * 60 * minute));
			//			targetTS = new Timestamp(sourceTS.getTime() + (day * 1000 * 60 * 60 * 24));
		}
		return targetTS;
	}

	/**
	 * 현재날짜를 YYYYMMDDHHMMSS로 리턴
	 */
	public final synchronized static String getyyyyMMddHHmmss(){
		/** yyyyMMddHHmmss Date Format */
		SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
		return yyyyMMddHHmmss.format(new Date());
	}
	public final static String convertPaymentType(String type){
		if(type.toLowerCase().equals("card")){
			return "0";
		}else if(type.toLowerCase().equals("vbank")){
			return "2";
		}else if(type.equals("0")||type.equals("1")){
			return "CARD";
		}else if(type.equals("2")){
			return "VBANK";
		}
		return null;

	}

	public static boolean cancelNicePay(String idfPayment,float price){
		HttpServletRequest request =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		NicePayWebConnector connector = new NicePayWebConnector();
		connector.addRequestData("TID",idfPayment);
		connector.addRequestData("CancelAmt", ((int)price)+"");
		connector.addRequestData("CancelMsg","중복결제");
		connector.addRequestData("PartialCancelCode", "0");
		connector.setRequestData(request);
		connector.setNicePayHome(nicePayLogPath);
		connector.addRequestData("MID",MERCHANT_ID_1);
		connector.addRequestData("actionType", "CL0");
		connector.addRequestData("CancelPwd", "pensiond1");
		connector.addRequestData("CancelIP", "52.79.109.22");
		connector.requestAction();
		return connector.getResultData("ResultCode").equals("2001");
	}
}
