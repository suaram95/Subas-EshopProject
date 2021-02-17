package com.example.subaseshopproject.service;

import com.example.subaseshopproject.model.Blog;
import com.example.subaseshopproject.model.Product;
import com.example.subaseshopproject.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Optional<Blog> findBlogByName(String name){
        return blogRepository.findBlogByName(name);
    }

    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    public List<Blog> findTop3ByOrderByIdDesc(){
        return blogRepository.findTop3ByOrderByIdDesc();
    }

    public Optional<Blog> findById(long id){
        return blogRepository.findById(id);
    }

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public List<Blog> findAllByNameIgnoreCaseContaining(String name){
        return blogRepository.findAllByNameIgnoreCaseContaining(name);
    }
}
