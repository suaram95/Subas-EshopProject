package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.model.Color;
import com.example.subaseshopproject.model.OperatingSystem;
import com.example.subaseshopproject.model.Product;
import com.example.subaseshopproject.service.CategoryService;
import com.example.subaseshopproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/shop")
    public String shopPage(ModelMap map) {
        map.addAttribute("products", productService.findAll());
        map.addAttribute("categories", categoryService.findAll());
        map.addAttribute("lastThreeList", productService.findTop3ByOrderByIdDesc());

        map.addAttribute("colors", Color.values());
        map.addAttribute("opSystems", OperatingSystem.values());
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
        map.addAttribute("categories", categoryService.findAll());
        map.addAttribute("opSystems", OperatingSystem.values());
        map.addAttribute("lastThreeList", productService.findTop3ByOrderByIdDesc());
        return "single-product";
    }


}
