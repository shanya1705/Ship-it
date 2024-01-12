package com.gautami.shipit.controller;

import com.gautami.shipit.dto.CartItemDto;
import com.gautami.shipit.model.Cart;
import com.gautami.shipit.model.CartItem;
import com.gautami.shipit.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @PostMapping("/add/{cartId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Cart addItemToCart(@RequestBody CartItemDto cartItemDto, @PathVariable Long cartId){
        return cartItemService.addItemToCart(cartItemDto,cartId);
    }

}
