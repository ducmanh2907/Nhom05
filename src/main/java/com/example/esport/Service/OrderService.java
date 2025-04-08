package com.example.esport.Service;

import com.example.esport.model.CartItem;
import com.example.esport.model.Customer;
import com.example.esport.model.Order;
import com.example.esport.model.OrderDetail;
import com.example.esport.repository.OrderDetailRepository;
import com.example.esport.repository.OrderRepository;
import com.example.esport.repository.CustomerRepository;  // Giả sử bạn có CustomerRepository
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerRepository customerRepository; // Giả sử bạn có CustomerRepository

    @Transactional
    public Order createOrder(Customer customer, List<CartItem> cartItems) {
        // Tạo đơn hàng và liên kết với khách hàng
        Order order = new Order();
        order.setCustomer(customer);  // Liên kết với khách hàng
        order = orderRepository.save(order);

        // Lưu chi tiết đơn hàng từ giỏ hàng
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }

        // Xóa giỏ hàng sau khi tạo đơn hàng
        cartService.clearCart();

        return order;
    }
}
