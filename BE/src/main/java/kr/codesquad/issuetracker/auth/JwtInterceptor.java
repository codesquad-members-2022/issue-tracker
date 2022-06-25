package kr.codesquad.issuetracker.auth;

import static kr.codesquad.issuetracker.exception.ErrorMessage.MEMBER_NOT_FOUND;
import static kr.codesquad.issuetracker.exception.ErrorMessage.NOT_MATCH_HTTP_HEADER_FORMAT;

import com.auth0.jwt.interfaces.DecodedJWT;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.issuetracker.auth.annotation.LoginRequired;
import kr.codesquad.issuetracker.auth.service.JwtService;
import kr.codesquad.issuetracker.domain.member.repository.MemberRepository;
import kr.codesquad.issuetracker.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class JwtInterceptor implements HandlerInterceptor {

	private static final String AUTHORIZATION = "Authorization";

	private static final String BEARER = "Bearer";

	private final JwtService jwtService;

	private final MemberRepository memberRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (login(handler)) {
			hasMember(request);
		}
		return true;
	}

	private void hasMember(HttpServletRequest request) {
		String header = request.getHeader(AUTHORIZATION);
		isHeader(header);
		String jwt = header.substring(BEARER.length()).trim();
		DecodedJWT decodedJWT = jwtService.verifyToken(jwt);
		Long memberId = jwtService.getMemberId(decodedJWT);
		memberRepository.findById(memberId)
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
	}

	private void isHeader(String header) {
		if (header == null || !header.contains(BEARER)) {
			throw new CustomException(NOT_MATCH_HTTP_HEADER_FORMAT);
		}
	}

	private boolean login(Object handler) {
		return handler instanceof HandlerMethod
			&& ((HandlerMethod) handler).hasMethodAnnotation(LoginRequired.class);
	}
}
