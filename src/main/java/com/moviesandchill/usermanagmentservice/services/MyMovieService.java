package com.moviesandchill.usermanagmentservice.services;

import com.moviesandchill.usermanagmentservice.domain.Movie;
import org.springframework.stereotype.Service;
import com.moviesandchill.usermanagmentservice.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyMovieService implements MovieService {
    private MovieRepository movieRepository;

    @Override
    public List<Movie> listAll() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    @Override
    public Movie getById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
