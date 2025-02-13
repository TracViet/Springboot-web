package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.Users;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService; // Thêm ProductService để lấy danh sách sản phẩm

    @GetMapping("/cart")
    public String getCartItems(@AuthenticationPrincipal Users user, Model model) {
        List<CartItem> cartItems = cartService.getCartItems(user);
       List<Product> products = productService.getAllProducts();
       Long total = cartService.gettotal(user);
        model.addAttribute("total", total);
//        model.addAttribute("cartItems", cartService.getCartItems(user));
//        model.addAttribute("products", productService.getAllProducts());

        model.addAttribute("cartItems", cartItems != null ? cartItems : List.of());
        model.addAttribute("products", products != null ? products : List.of());

        return "cart";
    }


    @PostMapping("/cart/add")
    public String addToCart(@AuthenticationPrincipal Users user,
                            @RequestParam Long productId,
                            @RequestParam int quantity) {
        cartService.addToCart(user, productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }
}
