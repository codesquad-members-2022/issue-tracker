package codesquad.issuetracker.service;

import codesquad.issuetracker.dto.member.MemberDto;
import codesquad.issuetracker.dto.member.MemberDtos;
import codesquad.issuetracker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDtos getMembers() {
        return new MemberDtos(memberRepository.findAll());
    }

    public MemberDto getMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
