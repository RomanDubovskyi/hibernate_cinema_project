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
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;

import java.time.LocalDateTime;

import javax.security.sasl.AuthenticationException;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        Movie movie2 = new Movie();
        movie2.setTitle("Star Wars");
        movie2.setDescription("some description");
        movieService.add(movie2);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Premium Hall");
        cinemaHallService.add(cinemaHall);
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(150);
        cinemaHall2.setDescription("Basic Hall");
        cinemaHallService.add(cinemaHall2);

        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(LocalDateTime.now());
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        MovieSession movieSession2 = new MovieSession();
        movieSession2.setShowTime(LocalDateTime.now());
        movieSession2.setCinemaHall(cinemaHall2);
        movieSession2.setMovie(movie2);
        movieSessionService.add(movieSession2);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User user = authenticationService.register("email@gmail.com", "password");
        User user2 = authenticationService.register("email11@gmail.com", "password");
        try {
            System.out.println(authenticationService.login("email@gmail.com", "password"));
            System.out.println(authenticationService.login("email11@gmail.com", "password"));
        } catch (AuthenticationException e) {
            throw new RuntimeException();
        }

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

        shoppingCartService.addSession(movieSession, user2);
        shoppingCartService.addSession(movieSession, user);

        OrderService orderService =
                (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(shoppingCartService.getByUser(user2).getTickets(), user2);
        shoppingCartService.addSession(movieSession2, user2);
        orderService.completeOrder(shoppingCartService.getByUser(user2).getTickets(), user2);
        orderService.getOrderHistory(user2).forEach(System.out::println);

    }
}
