package com.example.demo.service;


import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    private Map<String, Product> productMap = new HashMap<>();

    public ProductService() {
        // Thêm một số sản phẩm mẫu
        productMap.put("1", new Product("Sản phẩm 1", 100.0, 10, "http://example.com/image1.png"));
        productMap.put("2", new Product("Sản phẩm 2", 150.5, 5, "http://example.com/image2.png"));
    }

    public Map<String, Product> getAllProducts() {
        System.out.println("Lấy product");
        return productMap;
    }

    public Product getProductById(String id) {
        return productMap.get(id);
    }

    public void addProduct(Product product) {
        String id = getNextKey(productMap);
        System.out.println("them product");
        productMap.put(id, product);
    }

    public String getNextKey(Map<String, Product> product) {
        int maxKey = product.keySet().stream()
                .mapToInt(Integer::parseInt) // Chuyển key từ String -> int
                .max() // Tìm giá trị lớn nhất
                .orElse(0); // Nếu không có phần tử nào, mặc định là 0
        return String.valueOf(maxKey + 1); // Cộng thêm 1 và chuyển thành String
    }
}