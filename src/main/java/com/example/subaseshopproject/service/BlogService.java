package com.example.subaseshopproject.service;

import com.example.subaseshopproject.model.Blog;
import com.example.subaseshopproject.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Optional<Blog> findBlogByName(String name){
        return blogRepository.findBlogByName(name);
    }

    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    public List<Blog> findTop3ByOrderByIdDesc(){
        return blogRepository.findTop3ByOrderByIdDesc();
    }
}
