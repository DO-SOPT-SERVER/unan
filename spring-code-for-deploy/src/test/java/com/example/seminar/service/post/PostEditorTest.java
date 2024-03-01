package com.example.seminar.service.post;


import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.Post;
import com.example.seminar.domain.SOPT;
import com.example.seminar.repository.PostJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PostEditorTest {

    @Autowired
    PostEditor postEditor;

    @Autowired
    PostSaver postSaver;

    @Autowired
    PostJpaRepository postJpaRepository;

    @Autowired
    PostRetriever postRetriever;

    @Test
    @DisplayName("게시글의 내용을 수정할 수 있다.")
    void editContent() {
        Member member = Member.builder()
                .age(20)
                .name("오해영")
                .nickname("오해영")
                .sopt(
                        SOPT.builder()
                                .part(Part.SERVER)
                                .build()
                ).build();
      // given
        Post post = Post.builder()
                .member(member)
                .title("제목")
                .content("내용")
                .build();
      // when
        Post savedPost = postJpaRepository.saveAndFlush(post);
        postEditor.editContent(savedPost.getId(), "수정된 내용");
        Post findPost = postRetriever.findById(savedPost.getId());
      // then
        Assertions.assertThat(findPost.getContent()).isEqualTo("수정된 내용");
    }
}
