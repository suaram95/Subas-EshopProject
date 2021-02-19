package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.model.*;
import com.example.subaseshopproject.service.BrandService;
import com.example.subaseshopproject.service.CategoryService;
import com.example.subaseshopproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @GetMapping("/shop")
    public String shopPage(ModelMap map) {
        map.addAttribute("products", productService.findAll());
        getAttributes(map);
        return "shop-left-sidebar";
    }

    @GetMapping("/singleProduct")
    public String singleProductPage(@RequestParam("id") long id, ModelMap map) {
        Optional<Product> productById = productService.findById(id);
        if (!productById.isPresent()) {
            return "redirect:/";
        }
        Product singleProduct = productById.get();
        map.addAttribute("singleProduct", singleProduct);

        map.addAttribute("productByBrandNotLikeSingleProdName",
                productService.findAllByBrandNotLikeSingleProductName(singleProduct.getName(),
                        singleProduct.getBrand().getId()));
        getAttributes(map);
        return "single-product";
    }

    @GetMapping("/productsByBrand")
    public String productsByBrand(@RequestParam("id") long brandId, ModelMap map) {
        Optional<Brand> brandById = brandService.findById(brandId);
        if (brandById.isPresent()) {
            Brand brand = brandById.get();
            map.addAttribute("productsByBrand", productService.findAllByBrand(brand));
            getAttributes(map);
            return "product-brand";
        }
        return "redirect:/";
    }

    @GetMapping("/productsByCategory")
    public String productsByCategory(@RequestParam("id") long categoryId, ModelMap map){
        Optional<Category> categoryById = categoryService.findById(categoryId);
        if (categoryById.isPresent()){
            Category category = categoryById.get();
            map.addAttribute("productsByCategory", productService.findAllByCategory(category));
            getAttributes(map);
            return "product-category";
        }
        return "redirect:/";
    }

    @GetMapping("/productsByColor")
    public String productsByColor(@RequestParam("color") Color color, ModelMap map){
        map.addAttribute("productsByColor",
                productService.findAllByColor(color));
        getAttributes(map);
        return "product-color";
    }

    @GetMapping("/productsByOpSystem")
    public String productsByOpSystem(@RequestParam("opSystem") OperatingSystem operatingSystem, ModelMap map){
        map.addAttribute("productsByOpSystem",
                productService.findAllByOperatingSystem(operatingSystem));
        getAttributes(map);
        return "product-opSystem";
    }

    @GetMapping("/productsByListType")
    public String productsByListType(@RequestParam("listType") ProductListType listType, ModelMap map){
        map.addAttribute("productsByListType",
                productService.findAllByProductListType(listType));
        getAttributes(map);
        return "product-listType";
    }


    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, ModelMap map) {
        List<Product> searchedProduct = productService.findAllByNameIgnoreCaseContaining(keyword);
        map.addAttribute("searchedProduct", searchedProduct);
        getAttributes(map);
        return "search";
    }

    //these attributes are sending in many controllers, for what is written this method
    private void getAttributes(ModelMap map) {
        map.addAttribute("categories", categoryService.findAll());
        map.addAttribute("colors", Color.values());
        map.addAttribute("opSystems", OperatingSystem.values());
        map.addAttribute("lastThreeList", productService.findTop3ByOrderByIdDesc());
    }


}
