package com.dev.cinema;

import com.dev.cinema.annotations.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.security.sasl.AuthenticationException;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Premium Hall");
        cinemaHallService.add(cinemaHall);
        System.out.println(cinemaHallService.getAll());

        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(LocalDateTime.now());
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);

        User user = new User();
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        UserService userService =
                (UserService) injector.getInstance(UserService.class);
        userService.add(user);
        System.out.println(userService.findByEmail("email@gmail.com"));

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        authenticationService.register("email11@gmail.com", "password");
        try {
            System.out.println(authenticationService.login("email@gmail.com", "password"));
            System.out.println(authenticationService.login("email11@gmail.com", "password"));
        } catch (AuthenticationException e) {
            throw new RuntimeException();
        }
    }
}
