package com.example.subaseshopproject.repository;

import com.example.subaseshopproject.model.Product;
import com.example.subaseshopproject.model.ProductListType;
import com.example.subaseshopproject.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByProductType(ProductType type);

    List<Product> findAllByProductListType(ProductListType type);

    List<Product> findTop3ByOrderByIdDesc();

    @Query("SELECT u FROM Product u WHERE u.name NOT LIKE:name AND u.brand.id=:brandId ")
    List<Product> findAllByBrandNotLikeSingleProductName(@Param("name") String name,
                                                         @Param("brandId") long brandId);

    List<Product> findAllByNameIgnoreCaseContaining(String keyword);

}
