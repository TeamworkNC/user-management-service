package com.moviesandchill.usermanagementservice.mapper;

import com.moviesandchill.usermanagementservice.dto.globalrole.GlobalRoleDto;
import com.moviesandchill.usermanagementservice.dto.globalrole.NewGlobalRoleDto;
import com.moviesandchill.usermanagementservice.dto.globalrole.UpdateGlobalRoleDto;
import com.moviesandchill.usermanagementservice.entity.GlobalRole;
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
public interface GlobalRoleMapper {
    GlobalRole mapToEntity(NewGlobalRoleDto dto);

    void updateEntity(@MappingTarget GlobalRole entity, UpdateGlobalRoleDto dto);

    List<GlobalRoleDto> mapToDto(List<GlobalRole> entities);

    GlobalRoleDto mapToDto(GlobalRole entity);
}
