package com.marketplace.mapper;

import com.marketplace.model.entity.OrderItem;
import com.marketplace.model.dto.OrderItemDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItemDTO toDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getProduct().getId(),
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getPriceAtOrder()
        );
    }
}
