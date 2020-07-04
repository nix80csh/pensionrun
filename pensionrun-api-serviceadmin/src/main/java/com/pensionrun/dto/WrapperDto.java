package com.pensionrun.dto;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WrapperDto {
	
	private List<PensionFacilityDto> pensionFacility;
	private List<PensionProvideDto> pensionProvide;
	private List<PensionThemeDto> pensionTheme;
	private List<PensionImageDto> pensionImage;
	private List<RecommendationPensionDto> recommendationPension;
	private List<RoomImageDto> roomImage;
	private List<RoomAmenityDto> roomAmenity;
	private List<RoomDto> room;
	private List<RoomPriceDto> roomPrice;
	private List<AdvertisementDto> advertisement;
	private List<RecommendationDto> recommendation;
	
	
	
}
