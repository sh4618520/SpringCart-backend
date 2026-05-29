package com.weekend.springcart.service;

import com.weekend.springcart.domain.Cart;
import com.weekend.springcart.domain.User;
import com.weekend.springcart.repository.CartRepository;
import com.weekend.springcart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public Long signUp(User user) {
        // 1. 유저 회원가입
        userRepository.save(user);

        // 2. 유저 장바구니 생성
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        return user.getId();
    }
}
