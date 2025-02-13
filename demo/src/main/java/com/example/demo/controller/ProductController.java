package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product-list"; // Trả về file product-list.html
    }

    @PostMapping("/products/add")
    public String addProduct(@RequestParam String name, @RequestParam double price,
                             @RequestParam int quantity, @RequestParam String imageUrl) {
        productService.addProduct(new Product(name, price, quantity, imageUrl));
        System.out.println("Product added");
        return "redirect:/products"; // Chuyển hướng về trang danh sách
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
