package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.dto.film.NewFilmDto;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.UserFavoriteFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/favorite-films",
        produces = "application/json"
)
@Slf4j
public class UserFavoriteFilmsController {

    private final UserFavoriteFilmService userFavoriteFilmService;


    public UserFavoriteFilmsController(UserFavoriteFilmService userFavoriteFilmService) {
        this.userFavoriteFilmService = userFavoriteFilmService;
    }

    @GetMapping
    List<FilmDto> getAllFavoriteFilms(@PathVariable long userId) throws UserNotFoundException {
        return userFavoriteFilmService.getAllFavoriteFilms(userId);
    }

    @PostMapping
    void addFavoriteFilm(@PathVariable long userId, @RequestBody NewFilmDto newFilmDto) throws UserNotFoundException {
        userFavoriteFilmService.addFavoriteFilm(userId, newFilmDto);
    }

    @DeleteMapping
    void deleteAllFavoriteFilms(@PathVariable long userId) throws UserNotFoundException {
        userFavoriteFilmService.deleteAllFavoriteFilms(userId);
    }

    @DeleteMapping("/{filmId}")
    void deleteFavoriteFilm(@PathVariable long userId, @PathVariable long filmId) throws UserNotFoundException, FilmNotFoundException {
        userFavoriteFilmService.deleteFavoriteFilm(userId, filmId);
    }
}
