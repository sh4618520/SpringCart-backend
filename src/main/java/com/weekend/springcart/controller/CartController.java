package com.weekend.springcart.controller;

import com.weekend.springcart.domain.CartItem;
import com.weekend.springcart.dto.CartItemDto;
import com.weekend.springcart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestParam("productId") Long productId,
            @RequestParam(value = "quantity", defaultValue = "1") int quantity) {

        // 임시 사용자 아이디
        Long temporaryUserId = 1L;

        // 서비스 호출해서 장바구니 담기 로직 실행
        Long cartItemId = cartService.addCart(temporaryUserId, productId, quantity);

        return ResponseEntity.ok("장바구니 담기 성공! (장바구니 ID: " + cartItemId + ")");
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getCartItems() {
        Long temporaryUserId = 1L;

        List<CartItem> cartItems = cartService.getCartItems(temporaryUserId);

        List<CartItemDto> result = cartItems.stream()
                .map(CartItemDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
