package com.moviesandchill.usermanagementservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {
    private String name;

    private LocalDate birthday;

    private String logoUrl;

    private String description;
}
