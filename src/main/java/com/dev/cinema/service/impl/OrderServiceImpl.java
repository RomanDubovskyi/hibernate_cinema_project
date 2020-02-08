package com.dev.cinema.service.impl;

import com.dev.cinema.annotations.Inject;
import com.dev.cinema.annotations.Service;
import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    ShoppingCartService shoppingCartService;
    @Inject
    OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTickets(tickets);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        orderDao.add(order);
        shoppingCartService.clearShoppingCart(shoppingCartService.getByUser(user));
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getUserOrders(user);
    }
}
