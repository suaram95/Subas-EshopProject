package com.example.subaseshopproject.service;


import com.example.subaseshopproject.model.Category;
import com.example.subaseshopproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
