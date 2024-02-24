package com.example.seminar.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickname;
    private int age;

    @Embedded
    private SOPT sopt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private final List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, int age, SOPT sopt) {
        validateAge(age);
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.sopt = sopt;
    }

    private void validateAge(final int age) {
        if (0 < age && age < 100) {
            throw new IllegalArgumentException("나이는 0보다 커야 합니다.");
        }
    }

    private void validateName(final String name) {

    }

    public void updateSOPT(SOPT sopt) {
        this.sopt = sopt;
    }
}
