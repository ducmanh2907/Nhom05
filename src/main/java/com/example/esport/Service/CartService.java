package com.example.esport.Service;

import com.example.esport.model.CartItem;
import com.example.esport.model.Product;
import com.example.esport.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;
    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
                cartItems.add(new CartItem(product, quantity));
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }
    public void clearCart() {
        cartItems.clear();
    }
    public double calculateTotalAmount() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0.0; // Trả về 0 nếu giỏ hàng không có mặt hàng
        }

        return cartItems.stream()
                .filter(Objects::nonNull) // Loại bỏ các phần tử null nếu có
                .mapToDouble(item -> {
                    if (item != null && item.getProduct() != null) {
                        return item.getQuantity() * item.getProduct().getPrice();
                    } else {
                        return 0.0; // Trả về 0 nếu item hoặc product là null
                    }
                })
                .sum();
    }
}