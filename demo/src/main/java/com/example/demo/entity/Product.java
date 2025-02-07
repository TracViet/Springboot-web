package com.example.demo.entity;

import java.util.Collection;

public class Product {
    //private int id;
    private String name;
    private double price;
    private int quantity;
    private String imageUrl;

    // Constructor
    public Product(String name, double price, int quantity, String imageUrl) {
        //this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
//    public int getid() {
//        return id;
//    }
//    public void setid(int id) {
//        this.id = id;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public boolean isEmpty() {
        if (name == null || price == 0 || quantity == 0)
        return true;
        return false;
    }


}