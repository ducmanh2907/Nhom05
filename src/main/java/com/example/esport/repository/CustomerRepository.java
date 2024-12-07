package com.example.esport.repository;

import com.example.esport.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByFullNameAndEmail(String fullName, String email);
    Customer findByFullNameAndEmail(String fullName, String email);
}