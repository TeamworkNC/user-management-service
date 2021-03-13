package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;

import java.util.List;

public interface UserWantWatchFilmService {
    List<FilmDto> getAllWantWatchFilms(long userId) throws UserNotFoundException;

    void addWantWatchFilm(long userId, long filmId) throws UserNotFoundException;

    void deleteAllWantWatchFilms(long userId) throws UserNotFoundException;

    void deleteWantWatchFilm(long userId, long filmId) throws UserNotFoundException, FilmNotFoundException;
}
