package com.example.subaseshopproject.dto;

import com.example.subaseshopproject.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {

    private long id;
    private String name;
    private Brand brand;
    private OperatingSystem operatingSystem;
    private double price;
    private Color color;
    private ProductType productType=ProductType.NORMAL;
    private ProductListType productListType=ProductListType.NEW_ARRIVAL;
    private Category category;

}
