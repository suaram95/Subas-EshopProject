package com.example.subaseshopproject.dto;

import com.example.subaseshopproject.model.Brand;
import com.example.subaseshopproject.model.Category;
import com.example.subaseshopproject.model.Color;
import com.example.subaseshopproject.model.OperatingSystem;
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
    private Category category;

}
