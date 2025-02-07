package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.UsersService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;


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
        String username = getContext().getAuthentication().getName();

        if ("admin".equals(username)) {
            return "admin"; // trả về trang admin
        } else {
            return "home"; // trả về trang home
        }
    }
}