package com.weekend.springcart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // 기본키

    @Column(nullable = false, unique = true, length = 100)
    private String email;       // 이메일

    @Column(nullable = false)
    private String password;    // 암호화된 비밀번호

    @Column(nullable = false, length = 50)
    private String name;        // 사용자 이름

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Purchase> orders = new ArrayList<>();
}
