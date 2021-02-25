package com.moviesandchill.usermanagementservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_pass")
@Data
public class UserPassword {
    String passwordHash;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userPasswordId;
    @OneToOne(optional = false, mappedBy = "password")
    private User user;
}
