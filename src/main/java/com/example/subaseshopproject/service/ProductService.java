package com.example.subaseshopproject.service;


import com.example.subaseshopproject.model.Blog;
import com.example.subaseshopproject.model.Product;
import com.example.subaseshopproject.model.ProductListType;
import com.example.subaseshopproject.model.ProductType;
import com.example.subaseshopproject.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

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

    public List<Product> findTop3ByOrderByIdDesc(){
        return productRepository.findTop3ByOrderByIdDesc();
    }

    public Optional<Product> findById(long id){
        return productRepository.findById(id);
    }
}
