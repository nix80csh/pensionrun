package com.pensionrun.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
@Data
@XmlRootElement(name="NewDataSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class WooriPensionAddPriceListVo {
	@XmlElement(name="Table")
	List<WooriPensionAddPriceVo> addPrices=new ArrayList<WooriPensionAddPriceVo>();
}
