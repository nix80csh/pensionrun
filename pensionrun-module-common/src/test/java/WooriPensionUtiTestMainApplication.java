import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.pensionrun.util.HttpClientUtil;
import com.pensionrun.util.XmlMarshalUtil;
import com.pensionrun.vo.WooriPensionAddPriceListVo;
import com.pensionrun.vo.WooriPensionAddPriceVo;

public class WooriPensionUtiTestMainApplication {
	public static void main(String[] args) throws Exception{
//		JAXBContext jaxbContext = JAXBContext.newInstance(WooriPensionAddPriceListVo.class);
//		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(HttpClientUtil.sendHttpClientGet("http://wif.wooripension.com/?p=PullAvailability&API_Key=b344946135bed64bb848d3ded92cd549&PropertyCode=w0101001&CheckInDate=20160701&CheckOutDate=20160702"));
		//        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/Users/lepus/test.txt"))));
	
		
//		WooriPensionAddPriceListVo prices = (WooriPensionAddPriceListVo) jaxbUnmarshaller.unmarshal(reader) ;
		System.out.println((WooriPensionAddPriceListVo)XmlMarshalUtil.xmlToClass(WooriPensionAddPriceListVo.class,reader));
		
		
		
		//        writer.write(HttpClientUtil.sendHttpClientGet("http://wif.wooripension.com/?p=PullAvailability&API_Key=b344946135bed64bb848d3ded92cd549&PropertyCode=w0101001&CheckInDate=20160701&CheckOutDate=20160702").replaceAll("<(/)?NewDataSet>", ""));
//		BufferedReader rd = null;
//		StringReader srd = null;
//
//
//		try {
//
//			rd = new BufferedReader(new FileReader("/Users/lepus/test.txt"));
//
//			String inputLine = null;
//
//			StringBuilder builder = new StringBuilder();
//
//			while((inputLine = rd.readLine()) != null)
//
//				builder.append(inputLine);
//
//			srd = new StringReader(builder.toString());
//		}catch(Exception e){
//
//		}
//
//		WooriPensionAddPriceVo prices = (WooriPensionAddPriceVo) jaxbUnmarshaller.unmarshal(srd) ;
//
//		System.out.println(prices);
	}
}
