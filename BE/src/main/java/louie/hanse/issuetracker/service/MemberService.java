package louie.hanse.issuetracker.service;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Member;
import louie.hanse.issuetracker.oauth.GithubUser;
import louie.hanse.issuetracker.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long login(GithubUser githubUser) {
        //            TODO 커스텀 예외 추가하기
        Member member = memberRepository.findBySocialId(githubUser.getLogin())
            .orElse(null);

        if (member == null) {
            member = new Member(githubUser.getLogin(), githubUser.getAvatarUrl());
            memberRepository.save(member);
        }
        return member.getId();
    }
}
