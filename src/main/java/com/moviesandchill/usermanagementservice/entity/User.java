package com.moviesandchill.usermanagementservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_info")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private LocalDate birthday;

    private String logoUrl;

    private String description;

    private LocalDate registrationDate = LocalDate.now();

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserPassword password;
}
