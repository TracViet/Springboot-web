package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") Users user) {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        usersService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
//        model.addAttribute("user", new Users());
        System.out.println("di qua login");
        return "login";
    }


    @GetMapping("/test")
   public String Index() {
       System.out.println("home");
       return "Product_page";
   }

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }
}