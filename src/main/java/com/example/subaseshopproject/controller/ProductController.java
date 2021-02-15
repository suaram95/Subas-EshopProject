package com.example.subaseshopproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/shop")
    public String shopPage(){
        return "shop-left-sidebar";
    }
}
