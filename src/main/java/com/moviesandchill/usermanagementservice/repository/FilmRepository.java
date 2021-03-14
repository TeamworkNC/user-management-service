package com.moviesandchill.usermanagementservice.repository;

import com.moviesandchill.usermanagementservice.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
