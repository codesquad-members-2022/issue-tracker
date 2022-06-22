package louie.hanse.issuetracker.service;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Member;
import louie.hanse.issuetracker.exception.MemberNotFoundException;
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
        Member member = memberRepository.findBySocialId(githubUser.getLogin())
                .orElse(null);

        if (member == null) {
            member = new Member(githubUser.getLogin(), githubUser.getAvatarUrl());
            memberRepository.save(member);
        }
        return member.getId();
    }

    public String findRefreshTokenById(Long id) {
        return findByIdOrThrow(id).getRefreshToken();
    }

    @Transactional
    public void updateRefreshToken(String refreshToken, Long id) {
        Member member = findByIdOrThrow(id);
        member.changeRefreshToken(refreshToken);
    }

    private Member findByIdOrThrow(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> {
                    throw new MemberNotFoundException("해당 id를 가진 회원이 존재하지 않습니다.");
                });
    }
}
