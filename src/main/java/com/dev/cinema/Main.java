package com.dev.cinema;

import com.dev.cinema.annotations.Injector;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);

        try {
            movieService.getAll().forEach(System.out::println);
        } catch (DataProcessingException e) {
            LOGGER.error("can't get all movies " + e);
        }
    }
}
