package com.moviesandchill.usermanagmentservice.repositories;

import com.moviesandchill.usermanagmentservice.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
