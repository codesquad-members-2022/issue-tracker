package dev.kukim.issues.user.auth.controller;

import dev.kukim.issues.user.auth.exception.AuthorizationException;
import dev.kukim.issues.user.auth.service.LoginTokenGenerator;
import dev.kukim.issues.user.user.domain.User;
import dev.kukim.issues.user.user.domain.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

	private final UserRepository userRepository;
	private final LoginTokenGenerator loginTokenGenerator;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) throws Exception {

		String authorization = request.getHeader("Authorization");
		if (authorization == null) {
			throw new AuthorizationException("로그인이 필요합니다.");
		}

		String token = authorization.substring("Bearer ".length());
		if (!loginTokenGenerator.validateToken(token)) {
			throw new AuthorizationException("유효하지 않은 토큰입니다.");
		}

		Long userId = loginTokenGenerator.getClaims(token).getBody().get("userId", Long.class);
		User findUser = userRepository.findById(userId)
			.orElseThrow(() -> new AuthorizationException("유효하지 않은 id입니다."));
		request.setAttribute("user", findUser);

		return true;
	}

}
