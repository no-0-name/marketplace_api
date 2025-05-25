package com.marketplace.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO  {
    private Long id;
    private Long userId;
    private List<CartItemDTO> items;
    private double cartTotal;

    public double getCartTotal() {
        double sum = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        return Math.round(sum * 100.0) / 100.0;
    }
}