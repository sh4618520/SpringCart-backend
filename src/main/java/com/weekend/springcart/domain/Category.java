package com.weekend.springcart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name; // 예: "패션", "전기/전자"

    // ⭐ 스크린샷에 있던 계층형 구조 (상위 카테고리 - 하위 카테고리) 구현!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent; // 내 위에 있는 부모 카테고리

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>(); // 내 밑에 있는 자식 카테고리들

    // ⭐ 이 카테고리에 속한 상품들 (1대N 관계)
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
