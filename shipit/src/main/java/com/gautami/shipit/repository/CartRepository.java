package com.gautami.shipit.repository;

import com.gautami.shipit.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}