package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.UserWantWatchFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(
        path = "api/v1/users/{userId}/want-watch-films",
        produces = "application/json"
)
@Slf4j
public class UserWantWatchFilmController {
    private final UserWantWatchFilmService userWantWatchFilmService;

    public UserWantWatchFilmController(UserWantWatchFilmService userWantWatchFilmService) {
        this.userWantWatchFilmService = userWantWatchFilmService;
    }

    @GetMapping
    public List<FilmDto> getAllWantWatchFilms(@PathVariable long userId) throws UserNotFoundException {
        return userWantWatchFilmService.getAllWantWatchFilms(userId);
    }

    @PostMapping
    public void addWantWatchFilm(@PathVariable long userId, @RequestBody long filmId) throws UserNotFoundException {
        userWantWatchFilmService.addWantWatchFilm(userId, filmId);
    }

    @DeleteMapping
    public void deleteAllWantWatchFilms(@PathVariable long userId) throws UserNotFoundException {
        userWantWatchFilmService.deleteAllWantWatchFilms(userId);
    }

    @DeleteMapping("/{filmId}")
    public void deleteWantWatchFilm(@PathVariable long userId, @PathVariable long filmId) throws UserNotFoundException, FilmNotFoundException {
        userWantWatchFilmService.deleteWantWatchFilm(userId, filmId);
    }
}
