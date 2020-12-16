package com.moviesandchill.usermanagmentservice.services;

import com.moviesandchill.usermanagmentservice.domain.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> listAll();
    Movie getById(Long id);
}
