package com.marketplace.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime orderDate;

    private String status;
    private List<OrderItemDTO> items;
    private double orderTotal;
    private String shippingAddress;
    private String paymentMethod;
    private String contactPhone;

    public double getOrderTotal() {
        double sum = items.stream()
                .mapToDouble(item -> item.getPriceAtOrder() * item.getQuantity())
                .sum();
        return Math.round(sum * 100.0) / 100.0;
    }
}
