package com.example.subaseshopproject.service;

import com.example.subaseshopproject.model.BlogCategory;
import com.example.subaseshopproject.repository.BlogCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogCategoryService {

    private final BlogCategoryRepository blogCategoryRepository;

    public List<BlogCategory> findAll(){
        return blogCategoryRepository.findAll();
    }

    public Optional<BlogCategory> findById(long categoryId) {
        return blogCategoryRepository.findById(categoryId);
    }
}
