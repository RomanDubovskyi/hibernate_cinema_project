package com.dev.cinema.service.impl;

import java.util.Collections;
import java.util.List;

import com.dev.cinema.annotations.Inject;
import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import org.apache.log4j.Logger;

public class MovieServiceImpl implements MovieService {
    private static final Logger LOGGER = Logger.getLogger(MovieServiceImpl.class);
    @Inject
    MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        try {
            return movieDao.getAll();
        } catch (DataProcessingException e) {
            LOGGER.error("Can't get all Movies " + e);
        }
        return Collections.emptyList();
    }
}
