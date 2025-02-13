package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.Users;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getCartItems(Users user) {
        System.out.println("getCartItems");
        System.out.println("user: " + user);
        if (cartItemRepository.findByUser(user) != null) {System.out.println("cartItemRepository.findByUser(user) isn't null");
        }
        return cartItemRepository.findByUser(user);
    }

    public CartItem addToCart(Users user, Long productId, int quantity) {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();


            CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);

            if (existingCartItem != null) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                return cartItemRepository.save(existingCartItem);
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                return cartItemRepository.save(cartItem);
            }
        }

        return null;
    }

    public long gettotal(Users user) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        return cartItems.stream()
                .mapToLong(item -> (long) (item.getProduct().getPrice() * item.getQuantity())) // Tính tổng tiền
                .sum();
    }


    public void removeFromCart(Long cartItemId) {
        Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);

        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();

            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepository.save(cartItem);
            } else {
                cartItemRepository.deleteById(cartItemId);
            }
        }
    }

}
