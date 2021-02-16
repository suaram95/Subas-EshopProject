package com.example.subaseshopproject.service;

import com.example.subaseshopproject.model.Brand;
import com.example.subaseshopproject.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public void save(Brand brand){
        brandRepository.save(brand);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
