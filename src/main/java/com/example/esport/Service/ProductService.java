package com.example.esport.Service;

import com.example.esport.model.Product;
import com.example.esport.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    // Retrieve all products from the database
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getProductsBySearch(String search) {
        return productRepository.findByNameContainingIgnoreCase(search); // Tìm kiếm sản phẩm theo tên
    }
    // Retrieve a product by its id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
    // Add a new product to the database
    public Product addProduct(Product product, MultipartFile image, MultipartFile images) {
        try {
            if (image != null && !image.isEmpty()) {
                String logo = saveImageStatic(image);
                product.setLogo(logo);
            }
            if (images != null && !images.isEmpty()) {
                String logos = saveImageStatic(images);
                product.setLogos(logos);
            }

            return productRepository.save(product);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }


    // Update an existing product
    public Product updateProduct(@NotNull Product product, MultipartFile image, MultipartFile images ) {
       Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        product.getId() + " does not exist."));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());


        try {
            if (image != null && !image.isEmpty()) {
                String logo = saveImageStatic(image);
                existingProduct.setLogo(logo);
            }
            if (images != null && !images.isEmpty()) {
                String logos = saveImageStatic(images);
                existingProduct.setLogos(logos);
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return productRepository.save(existingProduct);
    }


    private String saveImageStatic(MultipartFile image) throws IOException {
        File saveFile = new ClassPathResource("static/images").getFile();
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
    // Delete a product by its id
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }
}