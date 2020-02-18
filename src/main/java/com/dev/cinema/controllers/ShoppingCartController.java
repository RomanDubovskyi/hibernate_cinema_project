package com.dev.cinema.controllers;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shopping_carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieService movieService, CinemaHallService cinemaHallService,
                                  UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
        this.userService = userService;
    }

    @PostMapping(value = "add_movie_session")
    public void addMovieSessionToCart(@RequestBody MovieSessionRequestDto movieSessionRequestDto,
                                      @RequestParam Long userId) {
        MovieSession movieSession = transferDtoToMovieSession(movieSessionRequestDto);
        User user = userService.getById(userId);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping(value = "by_user")
    public List<TicketResponseDto> getByUser(@RequestParam Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(userService.getById(userId));
        return shoppingCart.getTickets().stream()
                .map(this::transferTicketToDto)
                .collect(Collectors.toList());
    }

    private TicketResponseDto transferTicketToDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setCinemaHallDescription(ticket.getCinemaHall().getDescription());
        ticketResponseDto.setMovieTitle(ticket.getMovie().getTitle());
        ticketResponseDto.setShowTime(ticket.getShowTime().toString());
        ticketResponseDto.setUserEmail(ticket.getUser().getEmail());
        return ticketResponseDto;
    }

    private MovieSession transferDtoToMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService
                .getById(movieSessionRequestDto.getCinemaHallId()));
        LocalDateTime localDateTime = LocalDateTime.parse(movieSessionRequestDto.getShowTime());
        movieSession.setShowTime(localDateTime);
        return movieSession;
    }
}
