package com.moviesandchill.usermanagmentservice.converts;

import org.springframework.core.convert.converter.Converter;
import com.moviesandchill.usermanagmentservice.commands.MovieForm;
import com.moviesandchill.usermanagmentservice.domain.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieToMovieForm implements Converter<Movie, MovieForm> {
    @Override
    public MovieForm convert(Movie movie) {
        MovieForm movieForm = new MovieForm();
        movieForm.setId(movie.getId());
        movieForm.setName(movie.getName());
        movieForm.setDescription(movie.getDescription());
        return movieForm;
    }
}
