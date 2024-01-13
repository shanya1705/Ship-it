package com.gautami.shipit.service;

import com.gautami.shipit.comminicatior.Notification;
import com.gautami.shipit.dto.OrderItemDto;
import com.gautami.shipit.exceptions.NotFound;
import com.gautami.shipit.model.Order;
import com.gautami.shipit.model.OrderItem;
import com.gautami.shipit.model.Product;
import com.gautami.shipit.model.User;
import com.gautami.shipit.repository.OrderRepository;
import com.gautami.shipit.repository.ProductRepository;
import com.gautami.shipit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Notification notification;

    public void placeOrder(List<OrderItemDto> orderItemDto, Long userId) {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new NotFound("User not found, can't place the order");
        }
        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order);
        Double price = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < orderItemDto.size(); i++) {
            OrderItem current = new OrderItem();
            Product product = productRepository.findById(orderItemDto.get(i).getProductId()).get();
            current.setPrice(orderItemDto.get(i).getPrice());
            current.setProduct(product);
            current.setQuantity(orderItemDto.get(i).getQuantity());
            current.setOrder(order);
            price = price + (orderItemDto.get(i).getPrice() * orderItemDto.get(i).getQuantity());
            orderItems.add(current);
        }
        order.setOrderItems(orderItems);
        order.setTotalPrice(price);
        orderRepository.save(order);
        notification.sendEmail(user.getEmail(),orderItemDto);
    }
}
