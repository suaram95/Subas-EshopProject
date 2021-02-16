package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.dto.ProductRequestDto;
import com.example.subaseshopproject.model.Brand;
import com.example.subaseshopproject.model.Product;
import com.example.subaseshopproject.service.BrandService;
import com.example.subaseshopproject.service.CategoryService;
import com.example.subaseshopproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Value("${file.upload.dir}")
    private String uploadDir;

    @GetMapping("/admin")
    public String adminPage(ModelMap map, @RequestParam(value = "msg", required = false) String msg){
        map.addAttribute("categories",categoryService.findAll());
        map.addAttribute("msg", msg);
        return "/adminPanel/admin";
    }

    @PostMapping("/admin/addBrand")
    public String addBrand(@ModelAttribute("brand") Brand brand,
                           @RequestParam("brandImage") MultipartFile multipartFile) throws IOException {
        String brandPic = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File image = new File(uploadDir, brandPic);
        multipartFile.transferTo(image);
        brand.setPicUrl(brandPic);
        brandService.save(brand);
        return "redirect:/admin?msg=Brand was added!";
    }

    @PostMapping("/admin/addProduct")
    public String addProduct(@ModelAttribute("product") ProductRequestDto productRequestDto,
                             @RequestParam("image")MultipartFile multipartFile) throws IOException {

        String productPic = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File image = new File(uploadDir, productPic);
        multipartFile.transferTo(image);

        Product product= Product.builder()
                .name(productRequestDto.getName())
                .brand(productRequestDto.getBrand())
                .operatingSystem(productRequestDto.getOperatingSystem())
                .price(productRequestDto.getPrice())
                .color(productRequestDto.getColor())
                .productType(productRequestDto.getProductType())
                .productListType(productRequestDto.getProductListType())
                .picUrl(productPic)
                .category(productRequestDto.getCategory())
                .build();
        productService.save(product);
        String msg="Product was successfully added!";
        return "redirect:/admin?msg="+msg;
    }
}
