package com.example.demo.repository;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Users;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(Users user);
    CartItem findByUserAndProduct(Users user, Product product);

}
