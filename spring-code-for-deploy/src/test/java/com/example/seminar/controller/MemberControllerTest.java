package com.example.seminar.controller;

import com.example.seminar.domain.Member;
import com.example.seminar.domain.Part;
import com.example.seminar.domain.SOPT;
import com.example.seminar.dto.request.member.MemberCreateRequest;
import com.example.seminar.dto.request.member.MemberProfileUpdateRequest;
import com.example.seminar.dto.response.MemberGetResponse;
import com.example.seminar.service.member.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = MemberController.class)
public class MemberControllerTest extends ControllerTestManager {

    @MockBean
    private MemberService memberService;
    @Test
    @DisplayName("신규 회원을 등록한다.")
    void createMember() throws Exception {

        // given
        when(memberService.create(any(MemberCreateRequest.class)))
                .thenReturn("/api/member/1");

        MemberCreateRequest request = new MemberCreateRequest(
                        "오해영",
                        "5hae0",
                        28,
                        SOPT.builder()
                                .part(Part.DESIGN)
                                .build()
                );

      // when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/member")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/api/member/1"))
        ;
    }

    @Test
    @DisplayName("특정 회원 정보를 조회한다.")
    void test() throws Exception {
      // given
        Member member = Member.builder()
                        .age(28)
                        .name("오해영")
                        .nickname("5hae0")
                        .sopt(
                                SOPT.builder()
                                        .part(Part.DESIGN)
                                        .build()
                        ).build();

        BDDMockito.given(memberService.getMemberById(1L))
                .willReturn(MemberGetResponse.of(member));

      // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/member/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("오해영"))
                .andExpect(MockMvcResultMatchers.jsonPath("age").value(28))
                .andExpect(MockMvcResultMatchers.jsonPath("nickname").value("5hae0"))
                .andExpect(MockMvcResultMatchers.jsonPath("soptInfo.part").value("DESIGN"))
                .andExpect(MockMvcResultMatchers.jsonPath("soptInfo.generation").value(34));

      // then

    }

    @Test
    @DisplayName("회원 정보 목록을 조회한다.")

    void getMembersProfile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/member"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("회원 정보를 수정한다.")
    void updateMemberSoptInfo() throws Exception {

        MemberProfileUpdateRequest request = new MemberProfileUpdateRequest((short) 6, Part.SERVER);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member/1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("회원 정보를 삭제한다.")
    void deleteMember() throws Exception {
      // given // when // then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/member/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }


}
