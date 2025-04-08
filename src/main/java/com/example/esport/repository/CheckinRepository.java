package com.example.esport.repository;

import com.example.esport.model.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    Checkin findByIdAndNameAndBeginAtAndFullnameAndEmail(Long id, String name, String beginAt, String fullname, String email);
}