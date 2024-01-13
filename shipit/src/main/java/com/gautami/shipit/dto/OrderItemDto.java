package com.gautami.shipit.dto;

import com.gautami.shipit.model.Order;
import com.gautami.shipit.model.OrderItem;
import com.gautami.shipit.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long  productId;
    private int quantity;
    private Double price;
}
