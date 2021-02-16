package com.example.subaseshopproject.repository;

import com.example.subaseshopproject.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findBlogByName(String name);

    List<Blog> findTop3ByOrderByIdDesc();
}
