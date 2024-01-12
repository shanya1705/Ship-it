package com.gautami.shipit.service;

import com.gautami.shipit.dto.CartDto;
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
        Cart cart=new Cart();
        cart.setTotalPrice(cartDto.getTotalPrice());
        User user=userRepository.findById(cartDto.getUserId()).get();
        cart.setUser(user);
        return cartRepository.save(cart);

    }
}
