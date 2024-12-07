package com.example.esport.Controller;

import com.example.esport.model.Product;
import com.example.esport.Service.ProductService;
import com.example.esport.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Hiển thị danh sách sản phẩm với tính năng tìm kiếm theo tên
    @GetMapping
    public String showProductList(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Product> products;

        if (search != null && !search.isEmpty()) {
            // Tìm sản phẩm khớp với từ khóa
            List<Product> searchResults = productService.getProductsBySearch(search);

            // Lấy các sản phẩm còn lại không khớp với từ khóa
            List<Product> remainingProducts = productService.getAllProducts().stream()
                    .filter(product -> !searchResults.contains(product))
                    .toList();

            // Sắp xếp kết quả tìm kiếm lên đầu
            products = new ArrayList<>(searchResults);
            products.addAll(remainingProducts);

            model.addAttribute("search", search); // Gửi từ khóa tìm kiếm về view
        } else {
            // Nếu không có tìm kiếm, hiển thị tất cả sản phẩm
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products); // Gửi danh sách sản phẩm về view
        return "products/product-list";
    }

    // Hiển thị form thêm sản phẩm
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories()); // Load categories
        return "products/add-product";
    }

    // Xử lý form thêm sản phẩm
    @PostMapping("/add")
    public String addProduct(@Valid Product product, @RequestParam("image") MultipartFile image,
                             @RequestParam("images") MultipartFile images, BindingResult result) {
        if (result.hasErrors()) {
            return "products/add-product";
        }
        productService.addProduct(product, image, images);
        return "redirect:/products";
    }

    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories()); // Load categories
        return "products/update-product";
    }

    // Xử lý form cập nhật sản phẩm
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid Product product, @RequestParam("image") MultipartFile image,
                                @RequestParam("images") MultipartFile images, BindingResult result) {
        if (result.hasErrors()) {
            product.setId(id); // Giữ lại ID nếu có lỗi
            return "products/update-product";
        }
        productService.updateProduct(product, image, images);
        return "redirect:/products";
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
