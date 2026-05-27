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
        Cart cart = cartRepository.findByUserId(userId).orElse(null);

        // 3. 아예 장바구니가 없다면
        if (cart == null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다!"));

            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        // 4. 해당 상품이 이미 장바구니에 있는지 확인
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

    @Transactional(readOnly = true)
    public List<CartItem> getCartItems(Long userId) {
       Cart cart = cartRepository.findByUserId(userId).orElse(null);
       if (cart == null) {
           return new ArrayList<>();
       }

       return cart.getCartItems();
    }
}
