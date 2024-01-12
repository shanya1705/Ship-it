package com.gautami.shipit.service;

import com.gautami.shipit.dto.CartItemDto;
import com.gautami.shipit.exceptions.NotFound;
import com.gautami.shipit.model.Cart;
import com.gautami.shipit.model.CartItem;
import com.gautami.shipit.model.Product;
import com.gautami.shipit.repository.CartItemRepository;
import com.gautami.shipit.repository.CartRepository;
import com.gautami.shipit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;

    public Cart addItemToCart(CartItemDto cartItemDto, Long cartId) {
        Cart cart=cartRepository.findById(cartId).get();
        if(cart==null){
            throw new NotFound("Cart for the user does not exist: "+cartId);
        }
        Product product=productRepository.findById(cartItemDto.getProduct_id()).get();
        if(product==null){
            throw new NotFound("Product you are trying to add does not exist: "+cartItemDto.getProduct_id());
        }
        CartItem cartItem=new CartItem();
        cartItem.setPrice(cartItemDto.getPrice());
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cart.setTotalPrice(cartItemDto.getQuantity()*cartItemDto.getPrice());
        List<CartItem> allItems=cart.getItems();
        allItems.add(cartItem);
        return cartRepository.save(cart);

    }
}
