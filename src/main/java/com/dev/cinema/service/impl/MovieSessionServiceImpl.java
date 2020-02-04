package com.dev.cinema.service.impl;

import com.dev.cinema.annotations.Inject;
import com.dev.cinema.annotations.Service;
import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private static final Logger LOGGER = Logger.getLogger(MovieSessionServiceImpl.class);
    @Inject
    MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        List<MovieSession> result = new ArrayList<>();
        try {
            for (MovieSession movieSession : movieSessionDao.getAll()) {
                if (movieSession.getShowTime().toLocalDate().equals(date)
                        && movieSession.getMovie().getId().equals(movieId)) {
                    result.add(movieSession);
                }
            }
        } catch (DataProcessingException e) {
            LOGGER.error("Can't get Sessions according to provided parameters " + e);
        }
        return result;
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
