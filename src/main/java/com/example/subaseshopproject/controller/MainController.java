package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.dto.UserRequestDto;
import com.example.subaseshopproject.model.Role;
import com.example.subaseshopproject.model.User;
import com.example.subaseshopproject.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(ModelMap map,
                            @RequestParam(value = "msg", required = false) String msg,
                            @RequestParam(value = "passMatchMsg", required = false) String passMatchMsg,
                            @ModelAttribute("user") UserRequestDto userRequestDto){
        map.addAttribute("msg", msg);
        map.addAttribute("passMatchMsg", passMatchMsg);
        return "login";
    }

    @GetMapping("/successLogin")
    public String successLogin(@AuthenticationPrincipal CurrentUser currentUser){
        if (currentUser==null){
            return "redirect:/";
        }
        User user = currentUser.getUser();
        if (user.getRole()== Role.ADMIN){
            return "admin";
        } else {
            return "redirect:/";
        }
    }


}
