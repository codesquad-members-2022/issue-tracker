package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.domain.Member;
import kr.codesquad.issuetracker.dto.GitHubUser;
import kr.codesquad.issuetracker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findLoginMember(GitHubUser gitHubUser){
        if (!memberRepository.findByGithubId(gitHubUser.getGithubId()).isPresent()){
            Member member = gitHubUser.createMember();
            memberRepository.save(member);
            return member;
        }
        return memberRepository.findByGithubId(gitHubUser.getGithubId()).orElseThrow(RuntimeException::new);
    }

}
