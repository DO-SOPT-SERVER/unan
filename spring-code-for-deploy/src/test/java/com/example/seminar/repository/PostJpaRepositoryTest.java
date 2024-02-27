package com.example.seminar.repository;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.Post;
import com.example.seminar.domain.SOPT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class PostJpaRepositoryTest {

    @Autowired
    PostJpaRepository postJpaRepository;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("사용자 이름으로 작성한 게시글을 모두 조회할 수 있다.")
    void findAllByMemberNameIn() {
      // given
        Member member1 = createMember("오해영");
        Member member2 = createMember("또오해영");
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);
        Post post1 = createPost("제목1", "내용1", member1);
        Post post2 = createPost("제목2", "내용2", member1);
        Post post3 = createPost("제목3", "내용3", member2);
        postJpaRepository.saveAll(List.of(post1, post2, post3));

        // when
        List<Post> findPosts = postJpaRepository.findAllByMemberNameIn(List.of("오해영", "또오해영"));

        // then
         Assertions.assertThat(findPosts)
                .extracting("title", "content")
                .containsExactlyInAnyOrder(
                        Assertions.tuple("제목1", "내용1"),
                        Assertions.tuple("제목2", "내용2"),
                        Assertions.tuple("제목3", "내용3")
                );
    }

    private Post createPost(String title, String content, Member member) {
         return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
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
