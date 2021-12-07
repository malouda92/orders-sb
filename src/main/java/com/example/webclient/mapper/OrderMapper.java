package com.example.webclient.mapper;

import com.example.webclient.dto.OrderDTO;
import com.example.webclient.models.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toOrderDTO(Order order);
    List<OrderDTO> toOrderDTOs(List<Order> orders);
    Order toOrder(OrderDTO orderDTO);
}
