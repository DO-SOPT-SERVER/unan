package com.example.seminar.repository;


import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("사용자 아이디로 사용자를 삭제할 수 있다.")
    void delete() {
      // given
        Member member = createMember("오에영");

        Member savedMember = memberJpaRepository.save(member);
        // when
        Member findMember = memberJpaRepository.findByIdOrThrow(savedMember.getId());
        memberRepository.remove(findMember);

        // then
        Assertions.assertThat(memberJpaRepository.findAll()).isEmpty();
        Assertions.assertThat(memberJpaRepository.findById(savedMember.getId())).isEmpty();


    }

    private Member createMember(String name) {
        SOPT sopt = SOPT.builder()
                .part(Part.SERVER)
                .build();
        return Member.builder()
                .age(99)
                .name(name)
                .sopt(sopt)
                .nickname("5hae0")
                .build();
    }

}
