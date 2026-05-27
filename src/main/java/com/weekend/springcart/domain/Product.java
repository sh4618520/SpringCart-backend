package com.weekend.springcart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;                        // 기본키

    @Column(nullable = false)
    private Integer price;                          // 상품 가격

    @Column(columnDefinition = "TEXT")
    private String description;                 // 상품 설명

    @Column(nullable = false)
    private Integer stockQuantity;                  // 재고량

    private String imageUrl;                    // 이미지 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
