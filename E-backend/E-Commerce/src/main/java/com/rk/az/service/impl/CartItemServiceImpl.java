package com.rk.az.service.impl;

import com.rk.az.model.CartItem;
import com.rk.az.model.User;
import com.rk.az.repository.CartItemRepository;
import com.rk.az.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
        CartItem item = findCartItemById(id);
        User cartItemUser= item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);
        }
        throw new Exception("you can't update this cartItem");
    }

    @Override
    public void removeCartItem(Long userId, Long carItemId) throws Exception {
        CartItem item = findCartItemById(carItemId);
        User cartItemUser= item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)){
            cartItemRepository.delete(item);
        }
        else {
            throw new Exception("you can't delete this cartItem");
        }
    }

    @Override
    public CartItem findCartItemById(Long id) throws Exception {

        return cartItemRepository.findById(id)
                        .orElseThrow(()-> new Exception("cart item not found with this id "+id));
    }
}
