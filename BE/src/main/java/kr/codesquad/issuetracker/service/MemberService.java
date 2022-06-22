package kr.codesquad.issuetracker.service;

import static kr.codesquad.issuetracker.exception.ErrorMessage.*;

import kr.codesquad.issuetracker.auth.GithubOauth;
import kr.codesquad.issuetracker.auth.dto.AccessTokenResponseDto;
import kr.codesquad.issuetracker.auth.dto.UserProfile;
import kr.codesquad.issuetracker.auth.service.JwtService;
import kr.codesquad.issuetracker.domain.member.Member;
import kr.codesquad.issuetracker.domain.member.MemberRepository;
import kr.codesquad.issuetracker.exception.CustomException;
import kr.codesquad.issuetracker.exception.ErrorMessage;
import kr.codesquad.issuetracker.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final GithubOauth oauth;
	private final JwtService jwtService;
	private final MemberRepository memberRepository;

	@Transactional
	public MemberResponseDto login(String code) {
		AccessTokenResponseDto token = oauth.getToken(code);
		log.debug("tokenResponse = {}", token);
		UserProfile userInfo = oauth.getUserInfo(token.getAccessToken());
		log.debug("userInfo = {}", userInfo);

		if (MemberDuplicateCheck(userInfo.getEmail())) {
			Member member = memberRepository.findByEmail(userInfo.getEmail())
				.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
			Member updateMember = member.update(userInfo, token.getAccessToken());
			return MemberResponseDto.of(updateMember, jwtService.createToken(updateMember));
		}
		Member member = Member.createMember(userInfo, token.getAccessToken());
		return MemberResponseDto.of(member, jwtService.createToken(memberRepository.save(member)));
	}

	private boolean MemberDuplicateCheck(String email) {
		return memberRepository.findByEmail(email).isPresent();
	}
}
