package com.rk.az.repository;

import com.rk.az.model.Cart;
import com.rk.az.model.CartItem;
import com.rk.az.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
