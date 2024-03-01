package com.example.seminar.domain;


import com.example.seminar.common.exception.PostException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class PostTest {


    @Test
    @DisplayName("제목이 50자를 넘으면, 게시글을 생성할 수 없다.")
    void postWithInvalidTitle() {
      // given
        String dummyTitle = "a".repeat(51);
      // when

        Assertions.assertThatThrownBy(
                () -> {
                    Post post = Post.builder()
                            .title(dummyTitle)
                            .member(createMember("오해영"))
                            .content("내용")
                            .build();
                }
        ).isInstanceOf(PostException.class)
                .hasMessage("제목은 50자 이하여야 합니다.");

      // then

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
