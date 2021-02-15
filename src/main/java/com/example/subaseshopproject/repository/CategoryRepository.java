package com.example.subaseshopproject.repository;

import com.example.subaseshopproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
