package com.example.seminar.domain;


import com.example.seminar.common.exception.MemberException;
import com.example.seminar.repository.MemberJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class MemberTest {

    @Test
    @DisplayName("100살이 넘은 사용자는 회원가입을 할 수 없다.")
    void memberRegister() {
        Assertions.assertThatThrownBy(
                () -> {
                    SOPT sopt = SOPT.builder()
                            .part(Part.SERVER)
                            .build();

                    Member member = Member.builder()
                            .age(101)
                            .name("오해영")
                            .sopt(sopt)
                            .nickname("5hae0")
                            .build();

                }
        ).isInstanceOf(MemberException.class)
        .hasMessage("회원의 나이는 0세 이상 100세 이하입니다.");
    }

//    @Test
//    @DisplayName("한국인 사용자만 가입할 수 있다.")
//    void memberWithNotKorean() {
//        Assertions.assertThatThrownBy(
//                () -> {
//                    SOPT sopt = SOPT.builder()
//                            .part(Part.SERVER)
//                            .build();
//
//                    Member member = Member.builder()
//                            .age(88)
//                            .name("mike")
//                            .sopt(sopt)
//                            .nickname("5hae0")
//                            .build();
//
//                }
//        ).isInstanceOf(MemberException.class)
//                .hasMessage("한글만 가능합니다.")
//        ;
//    }

    @Test
    @DisplayName("유저를 삭제한다.")
    void remove() {
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
        member.remove();

        // then
        Assertions.assertThat(member.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("SOPT 정보를 수정할 수 있다.")
    void updateSOPT() {
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
         member.updateSOPT(SOPT.builder()
                .part(Part.DESIGN)
                .build());

        // then
        Assertions.assertThat(member.getSopt()).extracting("generation", "part")
                .containsExactly((short) 34, Part.DESIGN);

    }
}
