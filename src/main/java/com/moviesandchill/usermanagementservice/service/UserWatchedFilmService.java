package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserWatchedFilmService {
    List<FilmDto> getAllWatchedFilms(long userId) throws UserNotFoundException;

    void addWatchedFilm(long userId, long filmId) throws UserNotFoundException;

    void deleteAllWatchedFilms(long userId) throws UserNotFoundException;

    void deleteWatchedFilm(long userId, long filmId) throws UserNotFoundException, FilmNotFoundException;
}
