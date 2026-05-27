package com.weekend.springcart.dto;

import com.weekend.springcart.domain.CartItem;
import com.weekend.springcart.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CartItemDto {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private int price;
    private int quantity;

    public CartItemDto(CartItem cartItem) {
        this.cartItemId = cartItem.getId();
        this.productId = cartItem.getProduct().getId();
        this.productName = cartItem.getProduct().getName();
        this.price = cartItem.getProduct().getPrice();
        this.quantity = cartItem.getQuantity();
    }
}
