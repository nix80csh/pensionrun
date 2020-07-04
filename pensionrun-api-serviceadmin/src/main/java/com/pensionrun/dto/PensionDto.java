package com.pensionrun.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PensionDto {

	private Integer idfPension;
	private Integer idfArea2;
	private String idfProvider;
	private String name;
	private String nameOwner;
	private String addr1;
	private String addr2;
	private String addrLoad1;
	private String addrLoad2;
	private Float latitude;
	private Float longitude;
	private String checkIn;
	private String checkOut;
	private String phone;
	private String mobileOwner;
	private String mobileManager;
	private Float addPriceAdult;
	private Float addPriceChild;
	private Float addPriceToddler;
	private String urlWebsite;
	private String descriptionTraffic1;
	private String descriptionTraffic2;
	private String description;
	private Character managementType;
	private Long roomCount;
	private Date regDate;


}
