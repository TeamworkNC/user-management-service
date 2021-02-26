package com.moviesandchill.usermanagementservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_pass")
@Data
public class UserPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPasswordId;

    String passwordHash;

    @OneToOne(optional = false, mappedBy = "password")
    private User user;
}
