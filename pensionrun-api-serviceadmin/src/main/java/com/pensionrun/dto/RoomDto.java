package com.pensionrun.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RoomDto {

	private Integer idfRoom;
	private Integer idfPension;
	private String idfProvider;
	private String idfProviderRoom;
	private String name;
	private Byte size;
	private Byte priority;
	private Byte peopleMin;
	private Byte peopleMax;
	private Character structureType;
	private String description;
	private String descriptionStyle;
	private Byte roomCount;
	private Byte bathroomCount;
	private Character state;
	private Date regDate;
	
	
}
