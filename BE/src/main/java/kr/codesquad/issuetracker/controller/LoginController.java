package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.domain.Member;
import kr.codesquad.issuetracker.dto.GitHubUser;
import kr.codesquad.issuetracker.dto.LoginResponse;
import kr.codesquad.issuetracker.jwt.JwtProvider;
import kr.codesquad.issuetracker.service.GithubOAuthService;
import kr.codesquad.issuetracker.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final GithubOAuthService githubOAuthService;
    private final JwtProvider jwtProvider;

    @GetMapping("/login/oauth/github")
    public LoginResponse login(@RequestParam String code){
        String accessToken = githubOAuthService.getAccessToken(code);
        GitHubUser githubUser = githubOAuthService.getGitHubUser(accessToken);
        Member loginMember = memberService.findLoginMember(githubUser);
        return jwtProvider.createLoginResponse(loginMember);
    }
}
