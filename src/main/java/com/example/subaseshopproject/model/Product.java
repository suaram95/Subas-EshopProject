package com.example.subaseshopproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @Enumerated(value = EnumType.STRING)
    private Brand brand;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "operating_system")
    private OperatingSystem operatingSystem;
    @Column
    private double price;
    @Enumerated(value = EnumType.STRING)
    private Color color;
    @Column
    private String picUrl;
    @ManyToOne
    private Category category;

}
