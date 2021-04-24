package com.moviesandchill.usermanagementservice.exception.film;

public class FilmNotFoundException extends Exception {
    public FilmNotFoundException(long filmId) {
        super("film with id " + filmId + " not found");
    }
}
