package com.moviesandchill.usermanagementservice.mapper;

import com.moviesandchill.usermanagementservice.dto.achievement.AchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.NewAchievementDto;
import com.moviesandchill.usermanagementservice.dto.achievement.UpdateAchievementDto;
import com.moviesandchill.usermanagementservice.entity.Achievement;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface AchievementMapper {
    Achievement mapToEntity(NewAchievementDto dto);

    Achievement updateEntity(@MappingTarget Achievement entity, UpdateAchievementDto dto);

    List<AchievementDto> mapToDto(List<Achievement> entities);

    AchievementDto mapToDto(Achievement entity);
}
