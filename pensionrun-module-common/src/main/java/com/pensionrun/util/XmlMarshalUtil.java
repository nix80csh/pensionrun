package com.pensionrun.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlMarshalUtil {
	public static Object xmlToClass(Class classType,StringReader reader) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(classType);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return jaxbUnmarshaller.unmarshal(reader) ;
	}

}
