package com.pensionrun.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.core.annotation.Order;

import com.pensionrun.vo.SlackVo;

public class SlackUtil {
	private static String slackWebHookUrl="https://slack.com/api/chat.postMessage";
	private static String slackTocken="xoxp-5086884970-17446872848-22334979751-30fb34df67";
	private static String slackChannel="C1C5TAQ1W";
	private static String serverUrl="test";
	public static  void sendNoticeSlackMessage(SlackVo slackVo) {
		// TODO Auto-generated method stub
		String text="";
		text+="[{\"pretext\": \""+slackVo.getNoitce()+"\",\"text\":\"";
		text+=" 이름= "+slackVo.getName();
		text+="  접수날짜= "+DateUtil.dateToString(slackVo.getRegDate(),"yyyy년 MM월 dd일 HH시mm분");
		text+=	"  예약날짜= "+DateUtil.dateFormatChange(slackVo.getCheckinDate(),"yyyyMMdd","yyyy년 MM월 dd일");
		text+=	"  결제수단= "+NicePayUtil.convertPaymentType(slackVo.getState()+"");
		text+=	"  우리펜션예약코드= "+(slackVo.getReservationCode()==null?"":slackVo.getReservationCode());
		text+=	"  예약코드= "+slackVo.getIdfOrder();
		text+="\\n<"+serverUrl+">";
		text+= "\",\"color\":\""+slackVo.getColor()+"\"}]";
		sendSlack(text);

	}
	public static void sendSlakMessage(SlackVo slackVo) {
		// TODO Auto-generated method stub
		sendSlack("[{\"pretext\": \""+slackVo.getNoitce()+"\",\"text\":\""+slackVo.getMessage()+ "\",\"color\":\""+slackVo.getColor()+"\"}]");
	}
	private static void sendSlack(String text){
		List <NameValuePair> param = new ArrayList <NameValuePair>();
		param.add(new BasicNameValuePair("token",slackTocken));
		param.add(new BasicNameValuePair("channel",slackChannel));
		param.add(new BasicNameValuePair("attachments",text));
		HttpClientUtil.sendHttpClientPost(slackWebHookUrl, param);
	}
}
