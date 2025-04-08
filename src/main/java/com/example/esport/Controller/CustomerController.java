package com.example.esport.Controller;

import com.example.esport.model.Customer;
import com.example.esport.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Hiển thị tất cả khách hàng
    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();  // Lấy tất cả khách hàng
        model.addAttribute("customers", customers);  // Thêm danh sách khách hàng vào model để truyền vào view
        return "customers/customer";  // Trả về tên view (thư mục templates)
    }
}
