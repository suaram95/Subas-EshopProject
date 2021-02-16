package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.model.Blog;
import com.example.subaseshopproject.service.BlogService;
import com.example.subaseshopproject.service.CommentService;
import com.example.subaseshopproject.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final CommentService commentService;

    @GetMapping("/singleBlog")
    public String singleBlogPage(@RequestParam(value = "id") long id,
                                 ModelMap map) {
        Optional<Blog> blogById = blogService.findById(id);
        if (!blogById.isPresent()) {
            return "redirect:/";
        }
        Blog blog = blogById.get();
        map.addAttribute("singleBlog", blog);
        map.addAttribute("comments", commentService.findAll());
        map.addAttribute("createdDate", DateUtil.getStringFromDate(blog.getCreatedDate()));
        return "single-blog";
    }
}
