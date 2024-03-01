package com.example.seminar.service.member;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StopWatch;

@SpringBootTest
@ActiveProfiles("test")
public class MemberSaverTest {

    @Autowired
    private MemberRegister memberRegister;

    @Test
    @DisplayName("SOPT 회원을 등록할 수 있다.")
    void register() {
        // given
        Member newMember = createMember("오해영");

        // when
        Member registeredMember = memberRegister.register(newMember);

        // then
        Assertions.assertThat(registeredMember)
                .extracting("age", "name", "sopt", "nickname")
                .containsExactly(99, "오해영", newMember.getSopt(), "5hae0");
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
