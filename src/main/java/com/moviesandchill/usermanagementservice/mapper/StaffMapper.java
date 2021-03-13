package com.moviesandchill.usermanagementservice.mapper;

import com.moviesandchill.usermanagementservice.dto.staff.StaffDto;
import com.moviesandchill.usermanagementservice.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface StaffMapper {
    Staff mapToEntity(Long staffId);

    List<StaffDto> mapToDto(List<Staff> entities);

    StaffDto mapToDto(Staff entity);
}
