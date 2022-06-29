package kr.codesquad.issuetracker.service;

import static kr.codesquad.issuetracker.exception.ErrorMessage.*;

import java.util.List;
import kr.codesquad.issuetracker.auth.GithubOauth;
import kr.codesquad.issuetracker.auth.dto.AccessTokenResponseDto;
import kr.codesquad.issuetracker.auth.dto.UserProfile;
import kr.codesquad.issuetracker.auth.service.JwtService;
import kr.codesquad.issuetracker.domain.member.Member;
import kr.codesquad.issuetracker.domain.member.repository.MemberRepository;
import kr.codesquad.issuetracker.exception.CustomException;
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

		if (isNotMember(userInfo.getEmail())) {
			return join(token, userInfo);
		}
		return login(token, userInfo);
	}

	private MemberResponseDto login(AccessTokenResponseDto token, UserProfile userInfo) {
		Member member = memberRepository.findByEmail(userInfo.getEmail())
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
		member.update(userInfo, token.getAccessToken());
		return MemberResponseDto.of(member, jwtService.createToken(member));
	}

	private MemberResponseDto join(AccessTokenResponseDto token, UserProfile userInfo) {
		Member member = Member.createMember(userInfo, token.getAccessToken());
		return 	MemberResponseDto.of(member, jwtService.createToken(memberRepository.save(member)));
	}

	private boolean isNotMember(String email) {
		return memberRepository.findByEmail(email).isEmpty();
	}

	public Member findUserById(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
	}

	public List<Member> findAssignees(List<Long> assignees) {
		return memberRepository.findAllById(assignees);
	}
}
