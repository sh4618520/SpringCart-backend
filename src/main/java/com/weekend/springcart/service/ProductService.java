package com.weekend.springcart.service;

import com.weekend.springcart.domain.Product;
import com.weekend.springcart.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * 전체 상품 목록 조회
     */
    public List<Product> findAllProducts() {
        // return productRepository.findAll();

        return productRepository.findAllWithCategory();
    }
}
