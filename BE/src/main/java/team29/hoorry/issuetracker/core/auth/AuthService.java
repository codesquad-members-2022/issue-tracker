package team29.hoorry.issuetracker.core.auth;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import team29.hoorry.issuetracker.core.auth.dto.AuthMemberResponse;
import team29.hoorry.issuetracker.core.auth.dto.AuthResponse;
import team29.hoorry.issuetracker.core.auth.dto.AuthToken;
import team29.hoorry.issuetracker.core.auth.dto.AuthTokenRequest;
import team29.hoorry.issuetracker.core.exception.ExceptionMessage;
import team29.hoorry.issuetracker.core.exception.InvalidCodeException;
import team29.hoorry.issuetracker.core.jwt.AccessToken;
import team29.hoorry.issuetracker.core.jwt.JwtGenerator;
import team29.hoorry.issuetracker.core.jwt.JwtRepository;
import team29.hoorry.issuetracker.core.jwt.RefreshToken;
import team29.hoorry.issuetracker.core.jwt.dto.JwtResponse;
import team29.hoorry.issuetracker.core.member.MemberRepository;
import team29.hoorry.issuetracker.core.member.domain.Member;
import team29.hoorry.issuetracker.core.member.dto.MemberResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final MemberRepository memberRepository;
	private final JwtRepository jwtRepository;

	public AuthResponse getAuthUser(String code) {
		AuthMemberResponse authMemberResponse = requestAuthUser(requestAuthToken(code));

		MemberResponse memberResponse = new MemberResponse();
		JwtResponse jwtResponse = new JwtResponse();

		Optional<Member> findMember = memberRepository.findByOauthId(authMemberResponse.getAuthId());
		if (findMember.isPresent()) {
			Member member = findMember.get();
			memberResponse = MemberResponse.from(member);
			AccessToken accessToken = JwtGenerator.generateAccessToken(member.getId());
			RefreshToken refreshToken = JwtGenerator.generateRefreshToken(member.getId());
			jwtRepository.save(refreshToken);
			jwtResponse = new JwtResponse(accessToken, refreshToken);
		}

		return new AuthResponse(findMember.isPresent(), authMemberResponse, memberResponse, jwtResponse);
	}

	private AuthToken requestAuthToken(String code) {
		return WebClient.create()
			.post()
			.uri("https://github.com/login/oauth/access_token")
			.headers(header -> {
				header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
			})
			.bodyValue(AuthTokenRequest.of(code))
			.retrieve()
			.bodyToMono(AuthToken.class)
			.block();
	}

	private AuthMemberResponse requestAuthUser(AuthToken authToken) {
		return WebClient.create()
			.get()
			.uri("https://api.github.com/user")
			.headers(header -> header.setBearerAuth(authToken.getAccessToken()))
			.retrieve()
			.bodyToMono(AuthMemberResponse.class)
			.doOnError(e -> {
				throw new InvalidCodeException(ExceptionMessage.INVALID_CODE_MESSAGE, e);
			})
			.block();
	}
}
