package com.moviesandchill.usermanagementservice.dto.achievement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAchievementDto {
    private String name;

    private String logoUrl;

    private String description;
}
