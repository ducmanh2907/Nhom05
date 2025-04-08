package com.example.esport.model;

import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "checkins")
public class Checkin {
    private Long productId; // ID của sản phẩm
    private String productName; // Tên sản phẩm
    private LocalDate beginAt; // Ngày bắt đầu
    private String fullName; // Tên đầy đủ của khách hàng
    private String email; // Email khách hàng
}
