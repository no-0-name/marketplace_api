package com.marketplace.controller;

import com.marketplace.model.dto.CartRequestDTO;
import com.marketplace.model.entity.User;
import com.marketplace.model.dto.CartDTO;
import com.marketplace.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@AuthenticationPrincipal User user, @RequestBody CartRequestDTO request) {
        cartService.addToCart(user, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok("Product added to cart");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeFromCart(@AuthenticationPrincipal User user, @RequestBody CartRequestDTO request) {
        cartService.removeFromCart(user, request.getProductId());
        return ResponseEntity.ok("Product removed from cart");
    }

    @PostMapping("/removeAll")
    public ResponseEntity<String> removeAllItemsFromCart(@AuthenticationPrincipal User user) {
        cartService.clearCart(user);
        return ResponseEntity.ok("All product removed from cart");
    }

    @GetMapping
    public CartDTO getCart(@AuthenticationPrincipal User user) {
        CartDTO cartDTO = cartService.getCart(user);
        return cartDTO;
    }

}
