package com.moviesandchill.usermanagementservice.service;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.entity.Film;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.FilmMapper;
import com.moviesandchill.usermanagementservice.repository.FilmRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserWatchedFilmService {

    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public UserWatchedFilmService(UserRepository userRepository, FilmRepository filmRepository, FilmMapper filmMapper) {
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    public List<FilmDto> getAllWatchedFilms(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        var films = new ArrayList<>(user.getWatchedFilms());
        return filmMapper.mapToDto(films);
    }

    public void addWatchedFilm(long userId, long filmId) throws UserNotFoundException {
        User user = findUserById(userId);
        Film film = filmMapper.mapToEntity(filmId);
        filmRepository.save(film);
        user.getWatchedFilms().add(film);
    }

    public void deleteAllWatchedFilms(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        user.getWatchedFilms().clear();
    }

    public void deleteWatchedFilm(long userId, long filmId) throws UserNotFoundException, FilmNotFoundException {
        User user = findUserById(userId);
        Film film = findFilmById(filmId);
        user.getWatchedFilms().remove(film);
    }

    private User findUserById(long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private Film findFilmById(long filmId) throws FilmNotFoundException {
        return filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException(filmId));
    }
}
