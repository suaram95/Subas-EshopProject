package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.dto.UserRequestDto;
import com.example.subaseshopproject.model.Role;
import com.example.subaseshopproject.model.User;
import com.example.subaseshopproject.repository.ProductRepository;
import com.example.subaseshopproject.security.CurrentUser;
import com.example.subaseshopproject.service.BrandService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class MainController {

    @Autowired
    private BrandService brandService;

    @Value("${file.upload.dir}")
    private String uploadDir;


    @GetMapping("/")
    public String mainPage(ModelMap map){
        map.addAttribute("brands", brandService.findAll());
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(ModelMap map,
                            @RequestParam(value = "msg", required = false) String msg,
                            @RequestParam(value = "passMatchMsg", required = false) String passMatchMsg,
                            @RequestParam(value = "activateMsg", required = false) String activateMsg,
                            @RequestParam(value = "activateFailMsg", required = false) String activateFailMsg,
                            @RequestParam(value = "successRegMsg", required = false) String successRegMsg,
                            @ModelAttribute("user") UserRequestDto userRequestDto){
        map.addAttribute("msg", msg);
        map.addAttribute("passMatchMsg", passMatchMsg);
        map.addAttribute("activateMsg", activateMsg);
        map.addAttribute("activateFailMsg", activateFailMsg);
        map.addAttribute("successRegMsg", successRegMsg);
        return "login";
    }

    @GetMapping("/successLogin")
    public String successLogin(@AuthenticationPrincipal CurrentUser currentUser){
        if (currentUser==null){
            return "redirect:/";
        }
        User user = currentUser.getUser();
        if (user.getRole()== Role.ADMIN){
            return "redirect:/admin";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }
}
