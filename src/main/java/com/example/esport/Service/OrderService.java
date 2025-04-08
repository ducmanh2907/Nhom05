package com.example.esport.Service;

import com.example.esport.model.CartItem;
<<<<<<< HEAD
import com.example.esport.model.Customer;
=======
>>>>>>> b2be986 (jonggun)
import com.example.esport.model.Order;
import com.example.esport.model.OrderDetail;
import com.example.esport.repository.OrderDetailRepository;
import com.example.esport.repository.OrderRepository;
<<<<<<< HEAD
import com.example.esport.repository.CustomerRepository;  // Giả sử bạn có CustomerRepository
=======
>>>>>>> b2be986 (jonggun)
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD

import java.util.List;

=======
import java.util.List;
>>>>>>> b2be986 (jonggun)
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
<<<<<<< HEAD

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
=======
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService; // Assuming you have a CartService
    @Transactional
    public Order createOrder(String customerName, List<CartItem> cartItems) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order = orderRepository.save(order);
>>>>>>> b2be986 (jonggun)
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
<<<<<<< HEAD
        }

        // Xóa giỏ hàng sau khi tạo đơn hàng
        cartService.clearCart();

=======
        }// Optionally clear the cart after order placement
        cartService.clearCart();
>>>>>>> b2be986 (jonggun)
        return order;
    }
}
