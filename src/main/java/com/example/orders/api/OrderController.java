package com.example.orders.api;

import com.example.orders.dto.OrderDTO;
import com.example.orders.services.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderServiceImpl orderService;

    @PreAuthorize("hasAuthority('ORDER_WRITE')")
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDTO));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER_ORDER')")
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @PreAuthorize("hasAuthority('ORDER_WRITE')")
    @PutMapping(path = "{id}")
    public ResponseEntity<OrderDTO> modifyOrder(@PathVariable Long id,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orederDate) {
        return ResponseEntity.ok(orderService.modifyOrder(id, orederDate));
    }

    @PreAuthorize("hasAuthority('ORDER_WRITE')")
    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

}
