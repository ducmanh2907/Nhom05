package com.example.esport.Controller;

import com.example.esport.model.Product;
import com.example.esport.Service.CategoryService;
import com.example.esport.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService; // Đảm bảo bạn đã injectCategoryService
    // Display a list of all products
    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/product-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories()); //Load categories
        return "products/add-product";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addProduct(@Valid Product product, @RequestParam("image") MultipartFile image, @RequestParam("images") MultipartFile images,   BindingResult result) {
        if (result.hasErrors()) {
            return "products/add-product";
        }
        productService.addProduct(product, image, images);
        return "redirect:/products";
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories()); //Load categories
        return "products/update-product";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid Product product, @RequestParam("image") MultipartFile image,@RequestParam("images") MultipartFile images,
                                BindingResult result) {
        if (result.hasErrors()) {
            product.setId(id); // set id to keep it in the form in case of errorsreturn "/products/update-product";
        }
        productService.updateProduct(product, image, images);
        return "redirect:/products";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
