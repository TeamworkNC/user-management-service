package com.moviesandchill.usermanagementservice.exception.film;

public class FilmNotFoundException extends Exception {
    public FilmNotFoundException() {
    }

    public FilmNotFoundException(long filmId) {
        super("film with id: " + filmId + " not found");
    }

    public FilmNotFoundException(String message) {
        super(message);
    }
}
