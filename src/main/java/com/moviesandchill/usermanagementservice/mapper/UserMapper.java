package com.moviesandchill.usermanagementservice.mapper;

import com.moviesandchill.usermanagementservice.dto.user.NewUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UpdateUserDto;
import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User mapToEntity(NewUserDto dto);

    void updateEntity(@MappingTarget User entity, UpdateUserDto dto);

    List<UserDto> mapToDto(List<User> entities);

    UserDto mapToDto(User entity);
}
