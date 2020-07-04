package com.pensionrun.task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.pensionrun.dao.RoomDao;
import com.pensionrun.dao.RoomPriceDao;
import com.pensionrun.dao.log.LogSchedulerDao;
import com.pensionrun.entity.RoomPrice;
import com.pensionrun.entity.RoomPriceId;
import com.pensionrun.entity.log.LogScheduler;
import com.pensionrun.util.WooriPensionProvider;

@Transactional
@Component
public class RoomPriceTask_1 {

	@Autowired
	RoomDao roomDao;
	@Autowired
	RoomPriceDao roomPriceDao;
	@Autowired
	LogSchedulerDao logSchedulerDao;

	@Scheduled(fixedDelay =  2 * 60 * 1000)
	public void syncRoomPrice() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

		LogScheduler logScheduler = new LogScheduler();
		int appliedCount = 0;
		
//		String previousUpdateDate = "2016-06-10 16:35:16";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String previousUpdateDate = sdf.format(logSchedulerDao.readMaxUpdDate('1'));
		System.out.println(previousUpdateDate);
		final String url = "http://wif.wooripension.com/?p=PullAvailabilityLastupdate&API_Key=b344946135bed64bb848d3ded92cd549&lastUpdate="
				+ previousUpdateDate;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(url);
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodelist = (NodeList) xpath.evaluate("/NewDataSet/Table", doc, XPathConstants.NODESET);
				
		for (int i = 0; i < nodelist.getLength(); i++) {

			String year = nodelist.item(i).getChildNodes().item(3).getTextContent().substring(0, 4);
			String month = nodelist.item(i).getChildNodes().item(3).getTextContent().substring(4, 6);
			String date = nodelist.item(i).getChildNodes().item(3).getTextContent().substring(6, 8);
			String checkDate = year + "-" + month + "-" + date;

			System.out.println("제공사 식별자 : " + nodelist.item(i).getChildNodes().item(1).getTextContent());
			System.out.println("제공사 객실 식별자 : " + nodelist.item(i).getChildNodes().item(5).getTextContent());
			System.out.println("체크인날짜 : " + checkDate);
			System.out.println("할인된 가격 : " + nodelist.item(i).getChildNodes().item(9).getTextContent());
			System.out.println("가격 : " + nodelist.item(i).getChildNodes().item(11).getTextContent());
			System.out.println("방상태 : " + nodelist.item(i).getChildNodes().item(13).getTextContent().charAt(0));
			System.out.println(i);
			System.out.println(nodelist.getLength());
			
			String idfProvider = nodelist.item(i).getChildNodes().item(1).getTextContent();
			String idfProviderRoom = nodelist.item(i).getChildNodes().item(5).getTextContent();
			Character type = '2';
			Float price = Float.parseFloat(nodelist.item(i).getChildNodes().item(11).getTextContent());
			Float priceDiscount = Float.parseFloat(nodelist.item(i).getChildNodes().item(9).getTextContent());
			byte remainRoomCount = Byte.parseByte(nodelist.item(i).getChildNodes().item(13).getTextContent());

			try {
				Integer idfRoom = (Integer) WooriPensionProvider.roomProvider.get(idfProvider).get(idfProviderRoom);

				RoomPrice roomPrice = new RoomPrice();
				RoomPriceId roomPriceId = new RoomPriceId();				
				roomPriceId.setIdfRoom(idfRoom);
				roomPriceId.setCheckDate(checkDate);

				// 가격이존재하지 않으면 인서트
				if (roomPriceDao.readById(roomPriceId) == null) {
					roomPrice.setId(roomPriceId);
					roomPrice.setType(type);
					roomPrice.setPrice(price);
					roomPrice.setPriceDiscount(priceDiscount);
					roomPrice.setRemainRoomCount(remainRoomCount);					
					roomPriceDao.create(roomPrice);
					appliedCount++;
				// 가격이 존재하면 업데이트
				} else {
					roomPrice.setId(roomPriceId);
					roomPrice.setType(type);
					roomPrice.setPrice(price);
					roomPrice.setPriceDiscount(priceDiscount);
					roomPrice.setRemainRoomCount(remainRoomCount);					
					roomPriceDao.update(roomPrice);
					appliedCount++;
				}

			} catch (NullPointerException e) {
				System.out.println("does not exist idfRoom");
			}
			
		}
		logScheduler.setType('1');
		logScheduler.setScheduledDate(new Date());
		logScheduler.setAppliedCount(appliedCount);
		logSchedulerDao.create(logScheduler);
	}
}
