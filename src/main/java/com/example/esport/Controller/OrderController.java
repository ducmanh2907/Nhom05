package com.example.esport.Controller;

<<<<<<< HEAD
import com.example.esport.Service.CartService;
import com.example.esport.Service.CustomerService;
import com.example.esport.Service.OrderService;
import com.example.esport.model.CartItem;
import com.example.esport.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    // Hiển thị form checkout
    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        model.addAttribute("customer", new Customer()); // Tạo một đối tượng Customer rỗng cho form
        return "cart/checkout"; // Trả về giao diện form checkout
    }

    // Xử lý lưu thông tin khách hàng và tạo đơn hàng
    @PostMapping("/submit")
    public String submitOrder(@ModelAttribute("customer") Customer customer) {
        // Lưu thông tin khách hàng vào cơ sở dữ liệu
        customerService.saveCustomer(customer);

        // Lấy danh sách sản phẩm trong giỏ hàng
        List<CartItem> cartItems = cartService.getCartItems();

        // Kiểm tra nếu giỏ hàng rỗng
        if (cartItems.isEmpty()) {
            return "redirect:/cart"; // Nếu giỏ hàng rỗng, chuyển hướng về trang giỏ hàng
        }

        // Tạo đơn hàng liên kết với khách hàng
        orderService.createOrder(customer, cartItems);

        // Xóa giỏ hàng sau khi đặt hàng thành công
        cartService.clearCart();

        return "redirect:/order/confirmation"; // Chuyển đến trang xác nhận đơn hàng
    }

    // Hiển thị thông báo xác nhận đặt hàng
    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "cart/order-confirmation"; // Trả về giao diện xác nhận
    }
}
=======
import com.example.esport.model.CartItem;
import com.example.esport.model.Order;
import com.example.esport.model.Product;
import com.example.esport.Service.CartService;
import com.example.esport.Service.OrderService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @GetMapping("/checkout")
    public String checkout() {
        return "/cart/checkout";
    }
    @PostMapping("/submit")
    public String submitOrder(String customerName) {
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems.isEmpty()) {
            return "redirect:/cart"; // Redirect if cart is empty
        }
        orderService.createOrder(customerName, cartItems);
        return "redirect:/order/confirmation";
    }
    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "cart/order-confirmation";
    }
}
>>>>>>> b2be986 (jonggun)
