package com.dev.cinema.service.impl;

import com.dev.cinema.annotations.Inject;
import com.dev.cinema.annotations.Service;
import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;

import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
