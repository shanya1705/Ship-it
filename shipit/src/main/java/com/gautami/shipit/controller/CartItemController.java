package com.gautami.shipit.controller;

import com.gautami.shipit.dto.CartItemDto;
import com.gautami.shipit.model.Cart;
import com.gautami.shipit.model.CartItem;
import com.gautami.shipit.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @PostMapping("/add/{cartId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void addItemToCart(@RequestBody CartItemDto cartItemDto, @PathVariable Long cartId){
         cartItemService.addItemToCart(cartItemDto,cartId);
    }

    @DeleteMapping("/delete/{cartId}/{cartItemId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void RemoveCartItem(@PathVariable Long cartId, @PathVariable Long cartItemId){
        cartItemService.removeItemFromCart(cartId,cartItemId);
    }

}
