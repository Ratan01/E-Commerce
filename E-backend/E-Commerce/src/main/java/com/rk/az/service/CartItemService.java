package com.rk.az.service;

import com.rk.az.model.CartItem;

public interface CartItemService {
    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception;
    void removeCartItem(Long userId, Long carItemId) throws Exception;
    CartItem findCartItemById(Long id) throws Exception;
}
