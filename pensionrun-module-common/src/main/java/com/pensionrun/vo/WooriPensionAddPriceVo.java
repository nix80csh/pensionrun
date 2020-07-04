package com.pensionrun.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
@Data
@XmlRootElement(name="Table")
@XmlAccessorType(XmlAccessType.FIELD)
public class WooriPensionAddPriceVo {
	@XmlElement(name="RoomCode")
	private String idfProvierRoom;
	@XmlElement(name="ExtraAdultPrice")
	private String addPriceAdult;
	@XmlElement(name="ExtraChildPrice")
	private String addPriceChild;
	@XmlElement(name="ExtraBabyPrice")
	private String addPriceToddler;


	public String getIdfProvierRoom() {
		return idfProvierRoom;
	}
	public void setIdfProvierRoom(String idfProvierRoom) {
		this.idfProvierRoom = idfProvierRoom;
	}
	public String getAddPriceAdult() {
		return addPriceAdult;
	}
	public void setAddPriceAdult(String addPriceAdult) {
		this.addPriceAdult = addPriceAdult;
	}
	public String getAddPriceChild() {
		return addPriceChild;
	}
	public void setAddPriceChild(String addPriceChild) {
		this.addPriceChild = addPriceChild;
	}
	public String getAddPriceToddler() {
		return addPriceToddler;
	}
	public void setAddPriceToddler(String addPriceToddler) {
		this.addPriceToddler = addPriceToddler;
	}
	@Override
	public String toString() {
		return "WooriPensionAddPriceVo [idfProvierRoom=" + idfProvierRoom + ", addPriceAdult=" + addPriceAdult
				+ ", addPriceChild=" + addPriceChild + ", addPriceToddler=" + addPriceToddler + "]";
	}

}
