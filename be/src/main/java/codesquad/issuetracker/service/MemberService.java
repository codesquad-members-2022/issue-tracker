package codesquad.issuetracker.service;

import codesquad.issuetracker.dto.member.MemberDtoList;
import codesquad.issuetracker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDtoList getMembers() {
        return new MemberDtoList(memberRepository.findAll());
    }

}
