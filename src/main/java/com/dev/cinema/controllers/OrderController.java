package com.dev.cinema.controllers;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService,
                           UserService userService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping(value = "/complete_order")
    public void completeOrder(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.findByEmail(userRequestDto.getEmail());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(), user);
        shoppingCartService.clearShoppingCart(shoppingCart);
    }

    @GetMapping(value = "/all")
    public List<TicketResponseDto> getAll() {
        return orderService.getAll().stream()
                .flatMap(order -> order.getTickets().stream())
                .map(this::transferTicketToDto).collect(Collectors.toList());
    }

    private TicketResponseDto transferTicketToDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setCinemaHallDescription(ticket.getCinemaHall().getDescription());
        ticketResponseDto.setMovieTitle(ticket.getMovie().getTitle());
        ticketResponseDto.setShowTime(ticket.getShowTime().toString());
        ticketResponseDto.setUserEmail(ticket.getUser().getEmail());
        return ticketResponseDto;
    }
}
