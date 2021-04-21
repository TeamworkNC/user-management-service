package com.moviesandchill.usermanagementservice.mapper;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.dto.film.NewFilmDto;
import com.moviesandchill.usermanagementservice.dto.messrequest.MessRequestDto;
import com.moviesandchill.usermanagementservice.entity.Film;
import com.moviesandchill.usermanagementservice.entity.MessRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MessRequestMapper {

    MessRequest mapToEntity(MessRequestDto dto);

    MessRequest mapToEntity(Long messRequestId);

    List<MessRequestDto> mapToDto(List<MessRequest> entities);

    MessRequestDto mapToDto(MessRequest entity);
}
