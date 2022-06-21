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
//            TODO 커스텀 예외 추가하기
        return memberRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }
}
