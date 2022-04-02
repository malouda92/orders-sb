package com.example.orders.services;

import com.example.orders.dto.OrderDTO;
import com.example.orders.mapper.OrderMapper;
import com.example.orders.models.Order;
import com.example.orders.repositories.OrderRepository;
import com.example.orders.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return orderMapper.toOrderDTO(orderRepository.save(orderMapper.toOrder(orderDTO)));
    }

    @Override
    @Transactional
    public OrderDTO modifyOrder(Long id, LocalDate orderDate) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            if (orderDate != null) {
                orderOptional.get().setOrderDate(orderDate);
            }
            return orderMapper.toOrderDTO(orderOptional.get());
        }else {
            throw new IllegalStateException(Constants.ORDER_NOT_FOUND);
        }
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderMapper.toOrderDTO(orderOptional.get());
        }else {
            throw new IllegalStateException(Constants.ORDER_NOT_FOUND);
        }
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        return orderMapper.toOrderDTOs(orderRepository.findAll());
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalStateException("Commande introuvable");
        }
        orderRepository.deleteById(id);
    }
}
