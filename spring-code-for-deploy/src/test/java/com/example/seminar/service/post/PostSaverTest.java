package com.example.seminar.service.post;


import com.example.seminar.domain.Post;
import com.example.seminar.repository.PostJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class PostSaverTest {

    @Autowired
    PostSaver postSaver;

    @Autowired
    PostJpaRepository postJpaRepository;

    @Test
    @DisplayName("게시글을 저장한다.")
    void save() {
      // given
        Post post = Post.builder()
                .title("테스트 제목")
                .content("안녕하세요")
                .build();
      // when
        Post savedPost = postSaver.save(post);
      // then
        Assertions.assertThat(savedPost)
                .extracting("title", "content")
                .containsExactly("테스트 제목", "안녕하세요");
    }


}
