package com.weekend.springcart.controller;

import com.weekend.springcart.domain.Product;
import com.weekend.springcart.dto.ProductResponse;
import com.weekend.springcart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 프론트엔드에게 상품 리스트를 JSON 형태로 반환
     */
    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.findAllProducts().stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }
}
