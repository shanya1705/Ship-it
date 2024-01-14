package com.gautami.shipit.controller;

import com.gautami.shipit.dto.OrderItemDto;
import com.gautami.shipit.model.Order;
import com.gautami.shipit.model.OrderItem;
import com.gautami.shipit.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @HystrixCommand(fallbackMethod = "fallbackPlaceOrder")
    public String placeOrder(@RequestBody List<OrderItemDto> orderItemDto, @PathVariable Long userId){
        orderService.placeOrder(orderItemDto,userId);
        return "Order Placed";
    }

    public String fallbackPlaceOrder(@RequestBody List<OrderItemDto> orderItemDto, @PathVariable Long userId){
        return "Service is Not available";
    }

}
