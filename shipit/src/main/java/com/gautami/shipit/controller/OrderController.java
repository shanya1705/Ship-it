package com.gautami.shipit.controller;

import com.gautami.shipit.dto.OrderItemDto;
import com.gautami.shipit.model.Order;
import com.gautami.shipit.model.OrderItem;
import com.gautami.shipit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/{userId}")
    public void placeOrder(@RequestBody List<OrderItemDto> orderItemDto, @PathVariable Long userId){
        orderService.placeOrder(orderItemDto,userId);
    }
}
