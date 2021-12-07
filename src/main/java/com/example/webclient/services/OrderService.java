package com.example.webclient.services;

import com.example.webclient.models.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order modifyOrder(Long id, LocalDate orderDate);
    Order getOrderById(Long id);
    List<Order> getAllOrder();
    void deleteOrder(Long id);
}
