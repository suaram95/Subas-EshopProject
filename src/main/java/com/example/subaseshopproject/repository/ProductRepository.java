package com.example.subaseshopproject.repository;

import com.example.subaseshopproject.model.Product;
import com.example.subaseshopproject.model.ProductListType;
import com.example.subaseshopproject.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByProductType(ProductType type);

    List<Product> findAllByProductListType(ProductListType type);
}
