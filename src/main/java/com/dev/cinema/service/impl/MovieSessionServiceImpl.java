package com.dev.cinema.service.impl;

import com.dev.cinema.annotations.Inject;
import com.dev.cinema.annotations.Service;
import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private static final Logger LOGGER = Logger.getLogger(MovieSessionServiceImpl.class);
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
