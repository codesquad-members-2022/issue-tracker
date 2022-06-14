package com.ron2ader.issuetracker.service;

import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.domain.member.MemberRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto findMember(String userId) {
        return MemberDto.from(
            memberRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("해당하는 회원이 없습니다.")));
    }

}
