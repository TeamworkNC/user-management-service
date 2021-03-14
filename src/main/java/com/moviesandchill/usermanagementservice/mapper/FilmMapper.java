package com.moviesandchill.usermanagementservice.mapper;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.dto.film.NewFilmDto;
import com.moviesandchill.usermanagementservice.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface FilmMapper {
    Film mapToEntity(NewFilmDto dto);

    Film mapToEntity(Long filmId);

    List<FilmDto> mapToDto(List<Film> entities);

    FilmDto mapToDto(Film entity);
}
