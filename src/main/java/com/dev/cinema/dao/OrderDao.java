package com.dev.cinema.dao;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;

import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getUserOrders(User user);

    List<Order> getAll();

}
