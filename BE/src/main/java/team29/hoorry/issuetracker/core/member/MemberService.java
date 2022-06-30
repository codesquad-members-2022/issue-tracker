package team29.hoorry.issuetracker.core.member;

import io.jsonwebtoken.JwtException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team29.hoorry.issuetracker.core.exception.EmptyTokenException;
import team29.hoorry.issuetracker.core.exception.ExceptionMessage;
import team29.hoorry.issuetracker.core.jwt.AccessToken;
import team29.hoorry.issuetracker.core.jwt.JwtConst;
import team29.hoorry.issuetracker.core.jwt.JwtGenerator;
import team29.hoorry.issuetracker.core.jwt.JwtParser;
import team29.hoorry.issuetracker.core.jwt.JwtRedisRepository;
import team29.hoorry.issuetracker.core.jwt.JwtRepository;
import team29.hoorry.issuetracker.core.jwt.JwtValidator;
import team29.hoorry.issuetracker.core.jwt.RefreshToken;
import team29.hoorry.issuetracker.core.jwt.dto.JwtResponse;
import team29.hoorry.issuetracker.core.member.domain.Member;
import team29.hoorry.issuetracker.core.member.dto.MemberAndJwtResponse;
import team29.hoorry.issuetracker.core.member.dto.MemberLoginRequest;
import team29.hoorry.issuetracker.core.member.dto.MemberRequest;
import team29.hoorry.issuetracker.core.member.dto.MemberResponse;
import team29.hoorry.issuetracker.core.member.dto.MembersResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	private final JwtRepository jwtRepository;
	private final JwtRedisRepository jwtRedisRepository;
	private final JwtValidator jwtValidator;

	public MembersResponse findAll() {
		List<Member> members = memberRepository.findAll();
		List<MemberResponse> memberResponses = members.stream()
			.map(MemberResponse::from)
			.collect(Collectors.toList());
		return new MembersResponse(memberResponses);
	}

	public MemberAndJwtResponse join(MemberRequest memberRequest) {
		Member member = MemberRequest.toEntity(memberRequest);
		Member savedMember = memberRepository.save(member);
		MemberResponse memberResponse = MemberResponse.from(savedMember);

		AccessToken accessToken = JwtGenerator.generateAccessToken(member.getId());
		RefreshToken refreshToken = JwtGenerator.generateRefreshToken(member.getId());

		jwtRedisRepository.save(refreshToken);
		JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken);

		return new MemberAndJwtResponse(memberResponse, jwtResponse);
	}

	public JwtResponse reIssue(HttpServletRequest request) {
		String bearerAuthorization = request.getHeader(JwtConst.AUTHORIZATION);

		//todo 에러 메시지 전역적으로 관리해야 함!!! interceptor 에도 동일 메시지 있음!!!!!
		if (bearerAuthorization == null) {
			throw new EmptyTokenException(ExceptionMessage.NO_TOKEN_MESSAGE);
		}

		if (!jwtValidator.validate(bearerAuthorization)) {
			throw new JwtException(ExceptionMessage.INVALID_TOKEN_MESSAGE);
		}

		Long memberId = JwtParser.parseClaim(bearerAuthorization, "memberId", Long.class);
		AccessToken accessToken = JwtGenerator.generateAccessToken(memberId);
		RefreshToken refreshToken = JwtGenerator.generateRefreshToken(memberId);

		jwtRedisRepository.deleteById(JwtParser.extractBearerToken(bearerAuthorization));
		jwtRedisRepository.save(refreshToken);

		return new JwtResponse(accessToken, refreshToken);
	}

	public MemberAndJwtResponse login(MemberLoginRequest memberLoginRequest) {
		Member member = memberRepository.findByLoginId(memberLoginRequest.getLoginId())
			.orElseThrow(() -> new NoSuchElementException("해당 loginId를 가진 멤버는 없습니다."));

		if (!member.getLoginPassword().equals(memberLoginRequest.getLoginPassword())) {
			throw new NoSuchElementException("유효하지 않은 비밀번호입니다.");
		}
		MemberResponse memberResponse = MemberResponse.from(member);

		Long memberId = member.getId();
		AccessToken accessToken = JwtGenerator.generateAccessToken(memberId);
		RefreshToken refreshToken = JwtGenerator.generateRefreshToken(memberId);
		jwtRedisRepository.save(refreshToken);
		JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken);

		return new MemberAndJwtResponse(memberResponse, jwtResponse);
	}
}
