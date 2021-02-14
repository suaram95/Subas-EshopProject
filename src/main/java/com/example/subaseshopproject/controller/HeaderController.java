package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.model.User;
import com.example.subaseshopproject.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class HeaderController {

    @ModelAttribute("currentUser")
    public User getUser(@AuthenticationPrincipal CurrentUser currentUser){
        if (currentUser!=null){
            return currentUser.getUser();
        }
        return null;
    }
}
