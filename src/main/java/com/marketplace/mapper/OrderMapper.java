package com.marketplace.mapper;

import com.marketplace.model.entity.Order;
import com.marketplace.model.dto.OrderDTO;
import com.marketplace.model.dto.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OrderItemMapper orderItemMapper;

    public OrderDTO toDTO(Order order) {
        List<OrderItemDTO> itemDTOs = Optional.ofNullable(order.getItems())
                .orElse(Collections.emptyList())
                .stream()
                .map(orderItemMapper::toDTO)
                .collect(Collectors.toList());

        OrderDTO orderDTO = new OrderDTO(
                order.getId(),
                order.getUser().getId(),
                order.getOrderDate(),
                order.getStatus(),
                itemDTOs,
                order.getTotalAmount(),
                order.getShippingAddress(),
                order.getPaymentMethod(),
                order.getContactPhone()
        );

        orderDTO.setOrderTotal(orderDTO.getOrderTotal());
        return orderDTO;
    }
}
