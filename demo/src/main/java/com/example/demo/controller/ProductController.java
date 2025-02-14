package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Users;
import com.example.demo.service.ProductService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String listProducts(@AuthenticationPrincipal Users user,Model model) {
        if (checkAdmin(user)==false) { return "redirect:/cart"; }
        model.addAttribute("products", productService.getAllProducts());
        return "product-list"; // Trả về file product-list.html
    }

    @PostMapping("/products/add")
    public String addProduct(@AuthenticationPrincipal Users user, @RequestParam String name, @RequestParam double price,
                             @RequestParam int quantity, @RequestParam String imageUrl) {
        if (checkAdmin(user)==false) { return "redirect:/cart"; }
        productService.addProduct(new Product(name, price, quantity, imageUrl));
        System.out.println("Product added");
        return "redirect:/products"; // Chuyển hướng về trang danh sách
    }
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@AuthenticationPrincipal Users user,@PathVariable Long id) {
        if (checkAdmin(user)==false) { return "redirect:/cart"; }
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @PostMapping("/products/delete1item/{id}")
    public String delete1ItemProduct(@AuthenticationPrincipal Users user,@PathVariable Long id) {
        if (checkAdmin(user)==false) { return "redirect:/cart"; }
        productService.delete1itemProductById(id);
        return "redirect:/products";
    }

    @PostMapping("/products/add1item/{id}")
    public String add1item(@AuthenticationPrincipal Users user,@PathVariable Long id) {
        if (checkAdmin(user)==false) { return "redirect:/cart"; }
        productService.add1item(id);
        return "redirect:/products";
    }

    //@AuthenticationPrincipal
    public boolean checkAdmin( Users user) {
        String username = getContext().getAuthentication().getName();
        if ("admin".equals(username)) {
            return true;
        }
        else {
            System.out.println("URL traversal attack");
            return false;
        }
    }
}
