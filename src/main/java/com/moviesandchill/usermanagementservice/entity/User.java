package com.moviesandchill.usermanagementservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserPassword password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "friendship_info",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends;
}
