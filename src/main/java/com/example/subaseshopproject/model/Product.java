package com.example.subaseshopproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @ManyToOne
    private Brand brand;
    @Enumerated(value = EnumType.STRING)
    private OperatingSystem operatingSystem;
    @Column
    private double price;
    @Enumerated(value = EnumType.STRING)
    private Color color;
    @Enumerated(value = EnumType.STRING)
    private ProductType productType;
    @Enumerated(value = EnumType.STRING)
    private ProductListType productListType;
    @Column
    private String picUrl;
    @ManyToOne
    private Category category;

}
