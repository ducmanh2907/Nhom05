package com.example.esport.model;
<<<<<<< HEAD

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
=======
import jakarta.persistence.*;
import lombok.*;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "checkins")
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String beginAt;
    private String fullname;
    private String email;
>>>>>>> b2be986 (jonggun)
}
