package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;

import java.util.List;

public interface MovieSessionDao {
    List<MovieSession> getAll() throws DataProcessingException;

    MovieSession add(MovieSession session);
}
