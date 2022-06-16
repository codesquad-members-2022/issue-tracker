package com.ron2ader.issuetracker.controller;

import com.ron2ader.issuetracker.auth.github.GithubToken;
import com.ron2ader.issuetracker.auth.github.GithubUserInfo;
import com.ron2ader.issuetracker.auth.jwt.JwtProvider;
import com.ron2ader.issuetracker.controller.authdto.Tokens;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.domain.member.Member;
import com.ron2ader.issuetracker.service.GithubOAuthService;
import com.ron2ader.issuetracker.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final GithubOAuthService githubOAuthService;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @GetMapping("/auth/github")
    public Tokens requestAccessToken(String code) {
        GithubToken githubToken = githubOAuthService.requestAccessToken(code);
        GithubUserInfo githubUserInfo = githubOAuthService.requestUserInfo(githubToken);

        System.out.println("githubUserInfo.getUserId() = " + githubUserInfo.getUserId());

        Member member = memberService.upsert(Member.of(githubUserInfo.getUserId(), githubUserInfo.getAvatarUrl()));

        String accessToken = jwtProvider.generateAccessToken(member.getMemberId());
        String refreshToken = jwtProvider.generateRefreshToken(member.getMemberId());

        return Tokens.of(accessToken, refreshToken);
    }

    @GetMapping("/auth/refresh")
    public Tokens requestNewTokens(Member member) {
        MemberDto findMember = memberService.findMember(member.getMemberId());

        String accessToken = jwtProvider.generateAccessToken(findMember.getMemberId());
        String refreshToken = jwtProvider.generateRefreshToken(findMember.getMemberId());

        return Tokens.of(accessToken, refreshToken);
    }
}
