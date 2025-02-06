package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
//
//    @PostMapping("/login")
//    public String loginUser(Users user, Model model) {
//        System.out.println("test");
//        boolean isAuthenticated = usersService.loginUser(user);
//        if (isAuthenticated) {
//            return "redirect:/home";
//        } else {
//            model.addAttribute("loginError", true);
//            return "login";
//        }
//    }

//    public String login(@RequestParam String username, @RequestParam String password) {
//        boolean isLoggedIn = usersService.loginUser(username, password);
//        if (isLoggedIn) {
//            return "Login successful";
//        } else {
//            return "Invalid username or password";
//        }
//    }

    @GetMapping("/test")
   public String Index() {
       System.out.println("home");
       return "test";
   }

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }
}