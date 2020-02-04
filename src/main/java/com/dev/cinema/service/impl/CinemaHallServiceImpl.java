package com.dev.cinema.service.impl;

import com.dev.cinema.annotations.Inject;
import com.dev.cinema.annotations.Service;
import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;

import java.util.List;

import org.apache.log4j.Logger;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private static final Logger LOGGER = Logger.getLogger(CinemaHallServiceImpl.class);
    @Inject
    CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        try {
            return cinemaHallDao.getAll();
        } catch (DataProcessingException e) {
            LOGGER.error("Can't get all halls", e);
            throw new RuntimeException();
        }
    }
}
