package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        System.out.println("Id deleted: " + id);
        productRepository.deleteById(id);
    }
    public void delete1itemProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
            Product product = optionalProduct.get();
            if (product.getQuantity() > 0) {
                product.setQuantity(product.getQuantity() - 1);
                productRepository.save(product);
            } else {
                System.out.println("Quantity cannot be less than 0 for product with id: " + id);
                return;
            }

    }
    public void add1item(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
            Product product = optionalProduct.get();
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);

    }
}








