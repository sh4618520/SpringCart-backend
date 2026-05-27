package com.weekend.springcart.repository;

import com.weekend.springcart.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 상품을 가져올 때 카테고리까지 SQL 조인으로 한번에 묶어서 땡겨온다
    @Query("select p from Product p join fetch p.category")
    List<Product> findAllWithCategory();
}
