package com.weekend.springcart.dto;

import com.weekend.springcart.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private Integer stockQuantity;
    private String imageUrl;
    private String categoryName;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.stockQuantity = product.getStockQuantity();
        this.imageUrl = product.getImageUrl();
        this.categoryName = product.getCategory() != null ? product.getCategory().getName() : "미분류";
    }
}
