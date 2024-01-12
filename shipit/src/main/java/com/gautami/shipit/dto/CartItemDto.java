package com.gautami.shipit.dto;

import com.gautami.shipit.model.Cart;
import com.gautami.shipit.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long product_id;
    private int quantity;
    private Double price;
}
