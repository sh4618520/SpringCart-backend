package com.weekend.springcart;

import com.weekend.springcart.domain.Cart;
import com.weekend.springcart.domain.Category;
import com.weekend.springcart.domain.Product;
import com.weekend.springcart.domain.User;
import com.weekend.springcart.repository.CartRepository;
import com.weekend.springcart.repository.CategoryRepository;
import com.weekend.springcart.repository.ProductRepository;
import com.weekend.springcart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("========== 🚀 테스트 더미 데이터 주입 시작! ==========");

        // 1. 1번 테스트 유저 생성
        User user = new User();
        user.setEmail("test@test.com");
        user.setName("테스트유저");
        user.setPassword("1234");
        userRepository.save(user);

        Cart testCart = new Cart();
        testCart.setUser(user);
        cartRepository.save(testCart);

        // 2. 테스트용 상위 카테고리 생성 및 저장
        Category fashion = new Category();
        fashion.setName("패션/의류");
        categoryRepository.save(fashion);

        Category electronics = new Category();
        electronics.setName("디지털/가전");
        categoryRepository.save(electronics);

        // 3. 패션 카테고리에 속한 상품 등록
        Product p1 = new Product();
        p1.setCategory(fashion);
        p1.setName("커스텀 기모 맨투맨");
        p1.setPrice(39000);
        p1.setDescription("가을/겨울에 입기 좋은 따뜻한 오버핏 맨투맨입니다.");
        p1.setStockQuantity(100);
        p1.setImageUrl("https://images.unsplash.com/photo-1556905055-8f358a7a47b2");
        productRepository.save(p1);

        Product p2 = new Product();
        p2.setCategory(fashion);
        p2.setName("세미와이드 스트레이트 청바지");
        p2.setPrice(45000);
        p2.setDescription("자연스러운 워싱의 사계절용 세미 와이드 데님 팬츠.");
        p2.setStockQuantity(150);
        p2.setImageUrl("https://images.unsplash.com/photo-1542272604-787c3835535d");
        productRepository.save(p2);

        // 3. 가전 카테고리에 속한 상품 등록
        Product p3 = new Product();
        p3.setCategory(electronics);
        p3.setName("MacBook Air M4");
        p3.setPrice(1590000);
        p3.setDescription("얇고 가벼우며 역대급 성능을 자랑하는 M4 칩 탑재 맥북.");
        p3.setStockQuantity(50);
        p3.setImageUrl("https://images.unsplash.com/photo-1517336714731-489689fd1ca8");
        productRepository.save(p3);

        System.out.println("========== 🚀 테스트 더미 데이터 주입 완료! ==========");
    }
}
