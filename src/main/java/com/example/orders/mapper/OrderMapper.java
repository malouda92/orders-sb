package com.example.orders.mapper;

import com.example.orders.dto.OrderDTO;
import com.example.orders.models.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toOrderDTO(Order order);
    List<OrderDTO> toOrderDTOs(List<Order> orders);
    Order toOrder(OrderDTO orderDTO);
}
