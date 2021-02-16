package com.example.subaseshopproject.service;


import com.example.subaseshopproject.model.Product;
import com.example.subaseshopproject.model.ProductListType;
import com.example.subaseshopproject.model.ProductType;
import com.example.subaseshopproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> findAllByProductType(ProductType type){
        return productRepository.findAllByProductType(type);
    }

    public List<Product> findAllByProductListType(ProductListType type){
        return productRepository.findAllByProductListType(type);
    }
}
