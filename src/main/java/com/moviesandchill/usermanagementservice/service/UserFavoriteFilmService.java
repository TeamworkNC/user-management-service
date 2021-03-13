package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserFavoriteFilmService {
    List<FilmDto> getAllFavoriteFilms(long userId) throws UserNotFoundException;

    void addFavoriteFilm(long userId, long filmId) throws UserNotFoundException;

    void deleteAllFavoriteFilms(long userId) throws UserNotFoundException;

    void deleteFavoriteFilm(long userId, long filmId) throws UserNotFoundException, FilmNotFoundException;
}
