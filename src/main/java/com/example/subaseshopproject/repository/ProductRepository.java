package com.example.subaseshopproject.repository;

import com.example.subaseshopproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
