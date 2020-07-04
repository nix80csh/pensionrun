package com.pensionrun.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.pensionrun.vo.BiztokVo;


public class BiztokUtil {
	public static String BIZTOK_API="http://dev.pensionday.kr:8080/ata_server/biztalk/send";
	public static String changeSendType(char state){//예약코드를 템플릿 코드로 변경
		String sendType=null;
		switch(state){
		case '1':
			sendType="PR0012"; //예약 완료 안내
			break;
		case '5':
			sendType="PR0013"; //예약 취소 안내
			break;
		case '0':
			sendType="PR0009";// 온라인 입금 요청
			break;
		case '4':
			sendType="PR0015";//예약 취소 접수 안내	
			break;
		case 'e':
			sendType="PR0016";// 입급 기한 만료 안내 	
		}
		return sendType;
	}


	public static List<NameValuePair> setBiztokPostParam(BiztokVo biztokDto) {
		List <NameValuePair> param = new ArrayList <NameValuePair>();
		// TODO Auto-generated method stub
	   param.add(new BasicNameValuePair("user_name",biztokDto.getUserName()));
       param.add(new BasicNameValuePair("phone",biztokDto.getPhone()));
       param.add(new BasicNameValuePair("booking_number",biztokDto.getBookingNumber()));
       param.add(new BasicNameValuePair("inwon",biztokDto.getInwon()));
       param.add(new BasicNameValuePair("price",biztokDto.getPrice()));
       param.add(new BasicNameValuePair("total_day",biztokDto.getTotalDay()));
       param.add(new BasicNameValuePair("pension_name",biztokDto.getPensionName()));
       param.add(new BasicNameValuePair("room_name",biztokDto.getRoomName()));
       param.add(new BasicNameValuePair("send_type",biztokDto.getSendType()));
       
       if(biztokDto.getAccountNumber()!=null&&!biztokDto.getAccountNumber().equals("")){
    	   	param.add(new BasicNameValuePair("account_number",biztokDto.getAccountNumber()));
    	   	param.add(new BasicNameValuePair("account_number_time",biztokDto.getAccountNumberTime()));
       }
       return param;
	}
	public static void sendBiztok(BiztokVo biztokDto){
		HttpClientUtil.sendHttpClientPost(BiztokUtil.BIZTOK_API, BiztokUtil.setBiztokPostParam(biztokDto));
	}
	


}
