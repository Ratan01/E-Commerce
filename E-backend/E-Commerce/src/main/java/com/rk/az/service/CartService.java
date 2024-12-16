package com.rk.az.service;

import com.rk.az.model.Cart;
import com.rk.az.model.CartItem;
import com.rk.az.model.Product;
import com.rk.az.model.User;

public interface CartService {
    public CartItem addCartItem(User user, Product product, String size, int quantity);
    public Cart findUserCart(User user);
}
