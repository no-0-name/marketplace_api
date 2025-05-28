package com.marketplace.controller;

import com.marketplace.model.dto.OrderDTO;
import com.marketplace.model.dto.OrderRequestDTO;
import com.marketplace.model.entity.User;
import com.marketplace.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<String> createOrder(@AuthenticationPrincipal User user, @RequestBody OrderRequestDTO request) {
        OrderDTO orderDTO = orderService.createOrderFromCart(
                user.getId(),
                request.getShippingAddress(),
                request.getPaymentMethod(),
                request.getContactPhone()
        );
        return ResponseEntity.ok("Order was created");
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getUserOrders(@AuthenticationPrincipal User user) {
        List<OrderDTO> orders = orderService.getUserOrders(user.getId());
        return ResponseEntity.ok(orders);
    }
}