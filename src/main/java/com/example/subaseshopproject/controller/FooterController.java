package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.model.ProductListType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class FooterController {

    @ModelAttribute("productListTypes")
    public ProductListType[] getProductTypes() {
        return ProductListType.values();
    }
}
