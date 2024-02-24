package com.example.seminar.service.member;


import com.example.seminar.domain.Member;
import com.example.seminar.domain.SOPT;
import com.example.seminar.dto.request.member.MemberCreateRequest;
import com.example.seminar.dto.request.member.MemberProfileUpdateRequest;
import com.example.seminar.dto.response.MemberGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberSaver memberSaver;
    private final MemberRetriever memberRetriever;
    private final MemberUpdater memberUpdater;
    private final MemberRemover memberRemover;

    public MemberGetResponse getMemberById(final long id) {
        Member member = memberRetriever.findById(id);
        return MemberGetResponse.of(member);
    }

    public List<MemberGetResponse> getMembers() {
        return memberRetriever.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }
    @Transactional
    public String create(final MemberCreateRequest request) {
         final Member member =  memberSaver.save(Member.builder()
                 .name(request.getName())
                 .nickname(request.getNickname())
                 .age(request.getAge())
                 .sopt(request.getSopt())
                 .build());
         return member.getId().toString();
    }

    @Transactional
    public void updateSOPT(final long memberId, final MemberProfileUpdateRequest request) {
        final Member member = memberRetriever.findById(memberId);
        final SOPT sopt = SOPT.builder()
                .generation(request.getGeneration())
                .part(request.getPart())
                .build();
        memberUpdater.updateSopt(member, sopt);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRetriever.findById(memberId);
        memberRemover.remove(member);
    }
}
