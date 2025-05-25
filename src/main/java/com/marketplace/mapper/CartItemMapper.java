package com.marketplace.mapper;

import com.marketplace.model.entity.CartItem;
import com.marketplace.model.dto.CartItemDTO;
import com.marketplace.model.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemMapper {
    private final ProductMapper productMapper;

    public CartItemDTO toDTO(CartItem cartItem) {
        ProductDTO productDTO = productMapper.toDTO(cartItem.getProduct());
        return new CartItemDTO(
                cartItem.getId(),
                productDTO,
                cartItem.getQuantity()
        );
    }
}
