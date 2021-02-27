package com.moviesandchill.usermanagementservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_pass")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPassword {
    @Id
    @GeneratedValue
    private Long userPasswordId;

    String passwordHash;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
