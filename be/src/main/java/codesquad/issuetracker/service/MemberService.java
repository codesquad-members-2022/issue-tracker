package codesquad.issuetracker.service;

import codesquad.issuetracker.dto.member.MemberDtoList;
import codesquad.issuetracker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDtoList getMembers() {
        return new MemberDtoList(memberRepository.findAll());
    }

}
