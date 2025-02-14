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
        return cartItemRepository.findByUser(user);
    }
    public long gettotal(Users user) {
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        return cartItems.stream()
                .mapToLong(item -> (long) (item.getProduct().getPrice() * item.getQuantity())) // Tính tổng tiền
                .sum();
    }


    public CartItem addToCart(Users user, Long productId, int quantity) {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            System.out.println("quantity: " + quantity);
            if (product.getQuantity() < quantity) {
                System.out.println("Không đủ hàng trong kho!");
                return null;
            }

            CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
            CartItem cartItem;

            if (existingCartItem != null) {
                int newQuantity = existingCartItem.getQuantity() + quantity;

                existingCartItem.setQuantity(newQuantity);
                cartItem = cartItemRepository.save(existingCartItem);
            } else {
                cartItem = new CartItem();
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem = cartItemRepository.save(cartItem);
            }


            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            return cartItem;
        }

        return null;
    }


    public void removeFromCart(Long cartItemId) {
        Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);

        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            Product product = cartItem.getProduct();

            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemRepository.save(cartItem);
            } else {
                cartItemRepository.deleteById(cartItemId);
            }

            // Cộng lại số lượng vào kho
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);
        }
    }


}
