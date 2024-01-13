package com.gautami.shipit.controller;

import com.gautami.shipit.dto.CartDto;
import com.gautami.shipit.model.Cart;
import com.gautami.shipit.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Cart createCart(@RequestBody CartDto cartDto){
        return cartService.createCart(cartDto);
    }

    @GetMapping("/id/{cartId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Cart getCartForUser(@PathVariable Long cartId){
        return cartService.getCartForUser(cartId);
    }
}
