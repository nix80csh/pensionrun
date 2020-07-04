package com.pensionrun.util;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.pensionrun.vo.WooriPensionAddPriceListVo;
import com.pensionrun.vo.WooriPensionAddPriceVo;
import com.pensionrun.vo.WooriPensionVo;

public class WooriPensionUtil {
	private static String WOORIPENSION_API="http://wif.wooripension.com/";
	private static String WOORIPENSION_API_KEY="b344946135bed64bb848d3ded92cd549";
	private static String BASIC_DEPTH="/NewDataSet/Table";

	public static String reservationHolding(WooriPensionVo wooriPensionVo) throws UnsupportedEncodingException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String url=WOORIPENSION_API
				+"?p=ReservationHolding"
				+"&API_Key="+WOORIPENSION_API_KEY
				+"&OrderNo="+wooriPensionVo.getIdfOrder()
				+"&PropertyCode="+wooriPensionVo.getIdfProvider()
				+"&RoomCode="+wooriPensionVo.getIdfProvierRoom()
				+"&GuestName="+URLEncoder.encode(wooriPensionVo.getName(),"UTF-8")
				+"&GuestEmail="+wooriPensionVo.getEmail()
				+"&GuestPhoneNo="+wooriPensionVo.getMobile()
				+"&CheckInDate="+DateUtil.dateFormatChange(wooriPensionVo.getCheckinDate(), "yyyy-MM-ss","yyyyMMss")
				+"&CheckOutDate="+DateUtil.dateFormatChange(wooriPensionVo.getCheckoutDate(),"yyyy-MM-ss","yyyyMMss")
				+"&ReservationDate="+dateFormat.format(calendar.getTime())
				+"&GuestCount="+wooriPensionVo.getPeopleCount()
				+"&Discount="+(int)wooriPensionVo.getDiscount()
				+"&PaymentType="+wooriPensionVo.getType()
				+"&Payment="+(int)wooriPensionVo.getAmount()
				+"&ExtraPrice="+(int)wooriPensionVo.getExtraAmount();
		return getString(url,"/ReservationNo");
	}

	public static String reservationConfirm(WooriPensionVo wooriPensionVo){
		String url=WOORIPENSION_API
				+"?p=ReservationConfirm"
				+"&API_Key="+WOORIPENSION_API_KEY
				+"&OrderNo="+wooriPensionVo.getIdfOrder()
				+"&ReservationNo="+wooriPensionVo.getReservationCode()
				+"&PropertyCode="+wooriPensionVo.getIdfProvider()
				+"&RoomCode="+wooriPensionVo.getIdfProvierRoom();
		return getString(url,"/status");
	}
	public static String reservationCancellation(WooriPensionVo wooriPensionVo){
		String url=WOORIPENSION_API
				+"?p=ReservationCancellation"
				+"&API_Key="+WOORIPENSION_API_KEY
				+"&OrderNo="+wooriPensionVo.getIdfOrder()
				+"&Refund="+wooriPensionVo.getRefund()
				+"&ReservationNo="+wooriPensionVo.getReservationCode()
				+"&Payment="+(int)wooriPensionVo.getAmount();
		System.out.println(url);
		return getString(url,"/status");			
	}
	public static String reservationInfo(WooriPensionVo wooriPensionVo){
		String url=WOORIPENSION_API
				+"?p=ReservationInfo"
				+"&API_Key="+WOORIPENSION_API_KEY
				+"&CheckInDate="+DateUtil.dateFormatChange(wooriPensionVo.getCheckinDate(), "yyyy-MM-ss","yyyyMMss")
				+"&CheckOutDate="+DateUtil.dateFormatChange(wooriPensionVo.getCheckoutDate(),"yyyy-MM-ss","yyyyMMss")
				+"&OrderNo="+wooriPensionVo.getIdfOrder()
				+"&ReservationNo="+wooriPensionVo.getReservationCode();
		System.out.println(url);
		return getString(url,"/Status");	
	}
	
	public static String reservationCancelCharge(WooriPensionVo wooriPensionVo,String _date){	
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=null;
		String url=WOORIPENSION_API
				+"?p=ReservationCancelCharge"
				+"&API_Key="+WOORIPENSION_API_KEY
				+"&OrderNo="+wooriPensionVo.getIdfOrder()
				+"&ReservationNo="+wooriPensionVo.getReservationCode();
		NodeList nodeList=getNodeList(url);
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				NodeList childNodeList=nodeList.item(i).getChildNodes();
				if(nodeList.item(i).getChildNodes().item(1).getTextContent().equals(_date)){
					return nodeList.item(i).getChildNodes().item(3).getTextContent();
				}
			}
			return "0";
		}
		return null;
	}
//	public static int checkRemainRoom(OrderDto orderDto){
//		String url=WOORIPENSION_API
//				+"?p=ReservationCancelCharge"
//				+"&API_Key="+WOORIPENSION_API_KEY
//				+"&PropertyCode="+WooriPensionProvider.pensionProvierByIdfRoom.get(orderDto.getIdfRoom())
//				+"&CheckInDate="+orderDto.getCheckinDate()
//				+"&CheckOutDate="+orderDto.getCheckoutDate();
//		return Integer.parseInt(getString(url,"/RemainRoomCount"));
//	}
	public static List<WooriPensionAddPriceVo> readAddPriceList(WooriPensionVo wooriPensionVo) throws JAXBException{
		String url=WOORIPENSION_API
				+"?p=PullAvailability"
				+"&API_Key="+WOORIPENSION_API_KEY
				+"&PropertyCode="+wooriPensionVo.getIdfProvider()
				+"&CheckInDate="+wooriPensionVo.getCheckinDate()
				+"&CheckOutDate="+wooriPensionVo.getCheckoutDate();
		return ((WooriPensionAddPriceListVo)XmlMarshalUtil.xmlToClass(WooriPensionAddPriceListVo.class, new StringReader(HttpClientUtil.sendHttpClientGet(url)))).getAddPrices();
	}
	private static NodeList getNodeList(String url){
		try{
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
			XPath xpath = XPathFactory.newInstance().newXPath();
			return (NodeList)xpath.evaluate(BASIC_DEPTH, document, XPathConstants.NODESET);
		}catch(Exception e){
			return null;
		}
	}
	

	private static String getString(String url,String depth){
		try{
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
			return ((NodeList)(XPathFactory.newInstance().newXPath().evaluate(BASIC_DEPTH+depth, document, XPathConstants.NODESET))).item(0).getTextContent();
		}catch(Exception e){
			return null;
		}
	}
	

	public static int calcurateCancelCharege(WooriPensionVo wooriPensionVo,String date) throws ParseException{
		int remainDay=DateUtil.calculateDiffDay(date,wooriPensionVo.getCheckinDate(),"yyyy-MM-dd");
		int diffDay=DateUtil.calculateDiffDay(wooriPensionVo.getCheckinDate(),wooriPensionVo.getCheckoutDate(),"yyyy-MM-dd");
		int dayPrice=(int)(wooriPensionVo.getAmount()/diffDay);
		int cancelCharge=0;
		if(remainDay>7){
			return 0;
		}else if(remainDay>=1){
			double[] percent={0.7d,0.5d,0.3d,0.2d,0.1d,0.1d,0.1d,0d};
			int rateIndex=remainDay-1;
			for(int i=0;i<diffDay;i++){
				cancelCharge+=dayPrice*percent[rateIndex==7?7:rateIndex++];
			}
		}else{
			return -1;
		}
		return cancelCharge;
	}

}
