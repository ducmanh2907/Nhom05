package com.example.esport.model;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "cart_items")
public class CartItem {
    private Product product;
    private int quantity;
    // Constructors
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }// Getters and Setters
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
