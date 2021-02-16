package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.dto.UserRequestDto;
import com.example.subaseshopproject.model.User;
import com.example.subaseshopproject.security.CurrentUser;
import com.example.subaseshopproject.service.EmailService;
import com.example.subaseshopproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") UserRequestDto userRequestDto, BindingResult br, ModelMap map, Locale locale) throws MessagingException {
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
                .active(false)
                .token(UUID.randomUUID().toString())
                .build();
        userService.save(user);
        String link = "http://localhost:8080/user/activate?email=" + userRequestDto.getEmail() + "&token=" + user.getToken();
        emailService.sendHtmlEmail(user.getEmail(), "Activate Account", user, link, "email/userWelcomeEmail.html", locale);
        String successRegMsg = "You have successfully registered! Please check your email for account activation";
        return "redirect:/loginPage?successRegMsg=" + successRegMsg;
    }

    @GetMapping("/user/activate")
    public String activate(@RequestParam("email") String email,
                           @RequestParam("token") String token) {
        Optional<User> optionalUser = userService.findByUserName(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(true);
            user.setToken("");
            userService.save(user);
            return "redirect:/loginPage?activateMsg=Account was activated please login!";
        }
        return "redirect:/loginPage?activateFailMsg=Something went wrong";
    }

    @GetMapping("/user/account")
    public String userAccount(ModelMap map, @ModelAttribute("updateUser") User user,
                              @AuthenticationPrincipal CurrentUser currentUser,
                              @RequestParam(value = "passMatchMsg", required = false) String passMatchMsg) {
        map.addAttribute("passMatchMsg", passMatchMsg);
        map.addAttribute("currentUser", currentUser.getUser());
        return "my-account";
    }

    @PostMapping("/user/update")
    public String updateUser(@Valid @ModelAttribute("updateUser") UserRequestDto userRequestDto, BindingResult br, ModelMap map,
                             @RequestParam("id") long id,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("phone") String phone,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("additionalInfo") String additionalInfo) {
        if (br.hasErrors()) {
            map.addAttribute("users", userService.findAll());
            return "my-account";
        }
        if (!userRequestDto.getPassword().matches(userRequestDto.getConfirmPassword())) {
            String passMatchMsg = "Password and confirm password doesn't match";
            return "redirect:/user/account?passMatchMsg=" + passMatchMsg;
        }
        Optional<User> optionalUser = userService.findUserById(id);
        if (!optionalUser.isPresent()){
            return "redirect:/";
        }
        User user = optionalUser.get();
        if (!user.isActive()){
            return "redirect:/";
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setAdditionalInfo(additionalInfo);
        userService.save(user);

        return "redirect:/user/account";
    }


}
