
package com.pensionrun.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.Data;
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PensionListViewDto {
    
    private Integer idfPension;
    private String    addr1;
    private String name;
    private String image;
    private float price;
    private float priceDiscount;
    private String zzimState;   
    
    
}