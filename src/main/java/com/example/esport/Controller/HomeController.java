package com.example.esport.Controller;

import com.example.esport.model.Product;
import com.example.esport.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/layout")
    public String layout(Model model) {
        List<Product> Products = productService.getAllProducts();
        model.addAttribute("products", Products);
        return "/layout"; //
    }
}
