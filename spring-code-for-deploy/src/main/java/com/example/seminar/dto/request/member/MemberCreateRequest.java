package com.example.seminar.dto.request.member;


import com.example.seminar.domain.SOPT;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
<<<<<<< Updated upstream
    public MemberCreateRequest(String name, String nickname, int age, SOPT sopt) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.sopt = sopt;
    }
=======

>>>>>>> Stashed changes
}
