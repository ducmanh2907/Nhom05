package com.example.esport.Service;



import com.example.esport.model.Customer;
import com.example.esport.repository.CustomerRepository;

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
public class CustomerService {
    private final CustomerRepository  customerRepository;
    // Retrieve all products from the database

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();  // Truy vấn tất cả khách hàng
    }
}