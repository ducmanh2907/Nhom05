package com.example.esport.model;
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
}
