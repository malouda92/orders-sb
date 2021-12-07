package com.example.webclient.services;

import com.example.webclient.models.Order;
import com.example.webclient.repositories.OrderRepository;
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

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order modifyOrder(Long id, LocalDate orderDate) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            if (orderDate != null) {
                orderOptional.get().setOrderDate(orderDate);
            }
            return orderOptional.get();
        }else {
            throw new IllegalStateException("La commande n'existe pas");
        }
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalStateException("Commande introuvable");
        }
        orderRepository.deleteById(id);
    }
}
