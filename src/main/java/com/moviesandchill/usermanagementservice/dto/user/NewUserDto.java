package com.moviesandchill.usermanagementservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDto {
    private String login;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private String logoUrl;

    private String description;

    private String password;
}
