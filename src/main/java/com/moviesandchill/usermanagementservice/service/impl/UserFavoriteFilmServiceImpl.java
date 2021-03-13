package com.moviesandchill.usermanagementservice.service.impl;

import com.moviesandchill.usermanagementservice.dto.film.FilmDto;
import com.moviesandchill.usermanagementservice.dto.film.NewFilmDto;
import com.moviesandchill.usermanagementservice.entity.Film;
import com.moviesandchill.usermanagementservice.entity.User;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.mapper.FilmMapper;
import com.moviesandchill.usermanagementservice.repository.FilmRepository;
import com.moviesandchill.usermanagementservice.repository.UserRepository;
import com.moviesandchill.usermanagementservice.service.UserFavoriteFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserFavoriteFilmServiceImpl implements UserFavoriteFilmService {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final FilmMapper filmMapper;

    public UserFavoriteFilmServiceImpl(FilmRepository filmRepository, UserRepository userRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.filmMapper = filmMapper;
    }


    @Override
    public List<FilmDto> getAllFavoriteFilms(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        List<Film> films = new ArrayList<>(user.getFavoriteFilms());
        return filmMapper.mapToDto(films);
    }

    @Override
    public void addFavoriteFilm(long userId, NewFilmDto newFilmDto) throws UserNotFoundException {
        User user = findUserById(userId);
        Film film = filmMapper.mapToEntity(newFilmDto);
        film = filmRepository.save(film);
        user.getFavoriteFilms().add(film);
    }

    @Override
    public void deleteAllFavoriteFilms(long userId) throws UserNotFoundException {
        User user = findUserById(userId);
        user.getFavoriteFilms().clear();
    }

    @Override
    public void deleteFavoriteFilm(long userId, long filmId) throws UserNotFoundException, FilmNotFoundException {
        User user = findUserById(userId);
        Film film = findFilmById(filmId);
        user.getFavoriteFilms().remove(film);
    }

    private User findUserById(long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private Film findFilmById(long filmId) throws FilmNotFoundException {
        return filmRepository.findById(filmId).orElseThrow(FilmNotFoundException::new);
    }
}
