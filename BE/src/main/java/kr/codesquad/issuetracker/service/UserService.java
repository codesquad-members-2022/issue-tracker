package kr.codesquad.issuetracker.service;

import kr.codesquad.issuetracker.auth.GithubOauth;
import kr.codesquad.issuetracker.auth.dto.AccessTokenResponseDto;
import kr.codesquad.issuetracker.auth.dto.UserProfile;
import kr.codesquad.issuetracker.auth.service.JwtService;
import kr.codesquad.issuetracker.domain.member.Member;
import kr.codesquad.issuetracker.domain.member.MemberRepository;
import kr.codesquad.issuetracker.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	private final GithubOauth oauth;
	private final JwtService jwtService;
	private final MemberRepository memberRepository;

	@Transactional
	public UserResponseDto login(String code) {
		AccessTokenResponseDto token = oauth.getToken(code);
		log.debug("tokenResponse = {}", token);
		UserProfile userInfo = oauth.getUserInfo(token.getAccessToken());
		log.debug("userInfo = {}", userInfo);
		Member member = Member.createMember(userInfo, token.getAccessToken());
		return UserResponseDto.of(member, jwtService.createToken(memberRepository.save(member)));
	}
}
