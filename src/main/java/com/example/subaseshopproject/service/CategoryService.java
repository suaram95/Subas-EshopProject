package com.example.subaseshopproject.service;


import com.example.subaseshopproject.model.Category;
import com.example.subaseshopproject.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
