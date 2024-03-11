package com.example.seminar.repository;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@ActiveProfiles("test")
public class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("사용자 정보를 입력하면 회원을 등록할 수 있다.")
    void save() {
        // given
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

        Member member = Member.builder()
                .age(99)
                .name("오해영")
                .sopt(sopt)
                .nickname("5hae0")
                .build();

        // when
        Member savedMember = memberJpaRepository.save(member);

        // then
        Assertions.assertThat(savedMember)
                .extracting("age", "name", "sopt", "nickname")
                .containsExactly(99, "오해영", sopt, "5hae0");

    }

    @Test
    @DisplayName("사용자 id를 입력하면 회원을 조회할 수 있다. 존재하지 않는 사용자는 조회할 수 없다.")
    void findByIdOrThrow() {
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();

        Member member = Member.builder()
                .age(99)
                .name("오해영")
                .sopt(sopt)
                .nickname("5hae0")
                .build();

        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.findByIdOrThrow(savedMember.getId());

        Assertions.assertThat(findMember)
                .extracting("id", "age", "name", "sopt", "nickname")
                .containsExactly(savedMember.getId(), 99, "오해영", sopt, "5hae0");
    }

}
