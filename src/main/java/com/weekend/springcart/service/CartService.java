package com.weekend.springcart.service;

import com.weekend.springcart.domain.Cart;
import com.weekend.springcart.domain.CartItem;
import com.weekend.springcart.domain.Product;
import com.weekend.springcart.domain.User;
import com.weekend.springcart.repository.CartItemRepository;
import com.weekend.springcart.repository.CartRepository;
import com.weekend.springcart.repository.ProductRepository;
import com.weekend.springcart.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long addCart(Long userId, Long productId, int quantity) {

        // 1. 존재하는 상품인지 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다!"));

        // 2. 이 유저에게 장바구니가 있는지 조회
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저의 장바구니 개설이 되어 있지 않습니다. 유저 ID: " + userId));

        // 3. 해당 상품이 이미 장바구니에 있는지 확인
        CartItem alreadyCartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (alreadyCartItem != null) {
            alreadyCartItem.setQuantity(alreadyCartItem.getQuantity() + quantity);
            return alreadyCartItem.getId();
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);

            cart.getCartItems().add(newCartItem);

            cartItemRepository.save(newCartItem);
            return newCartItem.getId();
        }

    }

    @Transactional
    public void updateCartItemQuantity(Long cartItemId, int quantity) {
        // 1. 최소 수량은 1개 미만으로 내려가지 않는다
        if (quantity < 1) {
            throw new IllegalArgumentException("장바구니 수량은 최소 1개 이상이어야 합니다.");
        }

        // 2. 장바구니 항목 ID로 장바구니 아이템을 찾는다
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장바구니 항목이 존재하지 않습니다. id: " + cartItemId));

        // 3. 장바구니 항목 변경
        cartItem.setQuantity(quantity);
    }

    @Transactional
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장바구니 항목이 존재하지 않습니다. id: " + cartItemId));

        cartItemRepository.delete(cartItem);
    }

    @Transactional(readOnly = true)
    public List<CartItem> getCartItems(Long userId) {
       Cart cart = cartRepository.findByUserId(userId).orElse(null);
       if (cart == null) {
           return new ArrayList<>();
       }

       return cart.getCartItems();
    }
}
