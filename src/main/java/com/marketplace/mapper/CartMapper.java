package com.marketplace.mapper;

import com.marketplace.model.entity.Cart;
import com.marketplace.model.dto.CartDTO;
import com.marketplace.model.dto.CartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {
    private final CartItemMapper cartItemMapper;

    public CartDTO toDTO(Cart cart) {
        List<CartItemDTO> itemDTOs = Optional.ofNullable(cart.getCartItems())
                .orElse(Collections.emptyList())
                .stream()
                .map(cartItemMapper::toDTO)
                .collect(Collectors.toList());

        CartDTO cartDTO = new CartDTO(
                cart.getId(),
                cart.getUser().getId(),
                itemDTOs,
                0
        );

        cartDTO.setCartTotal(cartDTO.getCartTotal());
        return cartDTO;
    }
}
