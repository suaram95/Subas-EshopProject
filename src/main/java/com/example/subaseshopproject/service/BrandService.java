package com.example.subaseshopproject.service;

import com.example.subaseshopproject.model.Brand;
import com.example.subaseshopproject.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public void save(Brand brand){
        brandRepository.save(brand);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Optional<Brand> findById(long brandId) {
        return brandRepository.findById(brandId);
    }
}
