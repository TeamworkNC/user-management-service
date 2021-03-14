package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.entity.Film;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.FilmMapper;
import com.moviesandchill.usermanagementservice.repository.FilmRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import com.moviesandchill.usermanagementservice.service.UserWatchedFilmService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserWatchedFilmServiceImpl implements UserWatchedFilmService {

    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public UserWatchedFilmServiceImpl(UserRepository userRepository, FilmRepository filmRepository, FilmMapper filmMapper) {
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    @Override
    public List<FilmDto> getAllWatchedFilms(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        var films = new ArrayList<>(user.getWatchedFilms());
        return filmMapper.mapToDto(films);
    }

    @Override
    public void addWatchedFilm(long userId, long filmId) throws UserNotFoundException {
        User user = findUserById(userId);
        Film film = filmMapper.mapToEntity(filmId);
        filmRepository.save(film);
        user.getWatchedFilms().add(film);
    }

    @Override
    public void deleteAllWatchedFilms(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        user.getWatchedFilms().clear();
    }

    @Override
    public void deleteWatchedFilm(long userId, long filmId) throws UserNotFoundException, FilmNotFoundException {
        User user = findUserById(userId);
        Film film = findFilmById(filmId);
        user.getWatchedFilms().remove(film);
    }

    private User findUserById(long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private Film findFilmById(long filmId) throws FilmNotFoundException {
        return filmRepository.findById(filmId).orElseThrow(FilmNotFoundException::new);
    }
}
