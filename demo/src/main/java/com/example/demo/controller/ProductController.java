package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // Hiển thị danh sách sản phẩm
    @GetMapping("/Product")
    public String listProducts(Model model) {
        Map<String, Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "Product_page";
    }

    @GetMapping("/admin")
    public String adminpage(Model model) {
        Map<String, Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        System.out.println("adminpage");
        return "admin";
    }

    // Thêm sản phẩm mới
    @PostMapping("/addProduct")
    public String addProduct( @RequestParam String name,
                             @RequestParam double price, @RequestParam int quantity,
                             @RequestParam String imageUrl) {
        productService.addProduct(new Product(name, price, quantity, imageUrl));
        return "redirect:/Product";
    }
}