package com.gautami.shipit.service;

import com.gautami.shipit.dto.CartDto;
import com.gautami.shipit.exceptions.AlreadyExists;
import com.gautami.shipit.exceptions.NotFound;
import com.gautami.shipit.model.Cart;
import com.gautami.shipit.model.User;
import com.gautami.shipit.repository.CartRepository;
import com.gautami.shipit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    public Cart createCart(CartDto cartDto) {
        User user = userRepository.findById(cartDto.getUserId()).get();
        if(user.getCart()!=null){
            throw new AlreadyExists("A cart for the user already exists");
        }
        Cart cart = new Cart();
        cart.setTotalPrice(cartDto.getTotalPrice());
        cart.setUser(user);
        Cart newCart = cartRepository.save(cart);
        user.setCart(newCart);
        userRepository.save(user);
        return newCart;
    }

    public Cart getCartForUser(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new NotFound("cart with the given id not found"));
    }
}
