package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.model.Blog;
import com.example.subaseshopproject.model.BlogCategory;
import com.example.subaseshopproject.model.Color;
import com.example.subaseshopproject.model.OperatingSystem;
import com.example.subaseshopproject.service.BlogCategoryService;
import com.example.subaseshopproject.service.BlogService;
import com.example.subaseshopproject.service.CommentService;
import com.example.subaseshopproject.service.ProductService;
import com.example.subaseshopproject.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final CommentService commentService;
    private final BlogCategoryService blogCategoryService;
    private final ProductService productService;

    @GetMapping("/blog")
    public String blogPage(ModelMap map) {
        map.addAttribute("blogList", blogService.findAll());
        getAttributes(map);
        return "blog-left-sidebar";
    }

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
        getAttributes(map);
        return "single-blog";
    }

    @GetMapping("/blogsByCategory")
    public String blogsByCategory(@RequestParam("id") long categoryId, ModelMap map) {
        Optional<BlogCategory> categoryById = blogCategoryService.findById(categoryId);
        if (categoryById.isPresent()) {
            BlogCategory blogCategory = categoryById.get();
            map.addAttribute("blogsByCategory", blogService.findAllByBlogCategory(blogCategory));
            getAttributes(map);
            return "blog-category";
        }
        return "redirect:/";
    }

    @GetMapping("/searchBlog")
    public String searchBlog(@RequestParam("keyword") String keyword, ModelMap map) {
        List<Blog> searchedBlog = blogService.findAllByNameIgnoreCaseContaining(keyword);
        map.addAttribute("searchedBlog", searchedBlog);
        getAttributes(map);
        return "search-blog";
    }

    //these attributes are sending in many controllers, for what is written this method
    private void getAttributes(ModelMap map) {
        map.addAttribute("blogCategories", blogCategoryService.findAll());
        map.addAttribute("colors", Color.values());
        map.addAttribute("opSystems", OperatingSystem.values());
        map.addAttribute("latestThree", productService.findTop3ByOrderByIdDesc());
    }
}
