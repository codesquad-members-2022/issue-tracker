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
    public void login(GithubUser githubUser) {
        boolean existsBySocialId = memberRepository.existsBySocialId(githubUser.getLogin());
        if (!existsBySocialId) {
            Member member = new Member(githubUser.getLogin(), githubUser.getAvatarUrl());
            memberRepository.save(member);
        }
    }
}
