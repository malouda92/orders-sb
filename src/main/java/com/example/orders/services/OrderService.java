package com.example.orders.services;

import com.example.orders.dto.OrderDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO modifyOrder(Long id, LocalDate orderDate);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrder();
    void deleteOrder(Long id);
}
