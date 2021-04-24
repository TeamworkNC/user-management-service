package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.UserWatchedFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/wanted-films",
        produces = "application/json"
)
@Slf4j
public class UserWatchedFilmController {
    private final UserWatchedFilmService userWatchedFilmService;

    public UserWatchedFilmController(UserWatchedFilmService userWatchedFilmService) {
        this.userWatchedFilmService = userWatchedFilmService;
    }

    @GetMapping
    public List<FilmDto> getAllWatchedFilms(@PathVariable long userId) throws UserNotFoundException {
        return userWatchedFilmService.getAllWatchedFilms(userId);
    }

    @PostMapping
    public void addWatchedFilm(@PathVariable long userId, @RequestBody long filmId) throws UserNotFoundException {
        userWatchedFilmService.addWatchedFilm(userId, filmId);
    }

    @DeleteMapping
    public void deleteAllWatchedFilms(@PathVariable long userId) throws UserNotFoundException {
        userWatchedFilmService.deleteAllWatchedFilms(userId);
    }

    @DeleteMapping("/{filmId}")
    public void deleteWatchedFilm(@PathVariable long userId, @PathVariable long filmId) throws UserNotFoundException, FilmNotFoundException {
        userWatchedFilmService.deleteWatchedFilm(userId, filmId);
    }
}
