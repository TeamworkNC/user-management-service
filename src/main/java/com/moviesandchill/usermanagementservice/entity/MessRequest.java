package com.moviesandchill.usermanagementservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mess_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messRequestId;

    @Column(unique = true, nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User friend;
}
