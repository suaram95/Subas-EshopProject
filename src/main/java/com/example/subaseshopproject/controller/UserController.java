package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.dto.UserRequestDto;
import com.example.subaseshopproject.model.User;
import com.example.subaseshopproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRequestDto userRequestDto, BindingResult br, ModelMap map) {
        if (br.hasErrors()) {
            map.addAttribute("users", userService.findAll());
            return "login";
        }
        if (!userRequestDto.getPassword().matches(userRequestDto.getConfirmPassword())) {
            String passMatchMsg = "Password and confirm password doesn't match";
            return "redirect:/loginPage?passMatchMsg=" + passMatchMsg;
        }
        Optional<User> userByUserName = userService.findByUserName(userRequestDto.getEmail());
        if (userByUserName.isPresent()) {
            String msg = "User with email " + userRequestDto.getEmail() + " already exists!";
            return "redirect:/loginPage?msg=" + msg;
        }
        User user = User.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .phone(userRequestDto.getPhone())
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .role(userRequestDto.getRole())
                .build();
        userService.save(user);
        return "redirect:/";

    }
}
