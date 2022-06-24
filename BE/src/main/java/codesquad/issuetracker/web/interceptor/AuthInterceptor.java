package codesquad.issuetracker.web.interceptor;

import codesquad.issuetracker.domain.user.User;
import codesquad.issuetracker.domain.user.UserRepository;
import codesquad.issuetracker.web.jwt.JwtFactory;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

	private static final String TOKEN_TYPE = "Bearer ";
	private static final String USER_ID = "userId";

	private final JwtFactory jwtFactory;

	private final UserRepository userRepository;

	public AuthInterceptor(JwtFactory jwtFactory, UserRepository userRepository) {
		this.jwtFactory = jwtFactory;
		this.userRepository = userRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) throws Exception {

		String authorization = request.getHeader("Authorization");
		String token = parseToken(authorization);

		try {
			jwtFactory.validateToken(token);
		} catch (TokenExpiredException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("액세스 토큰이 만료되었습니다.");
		} catch (SignatureVerificationException | JWTDecodeException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("유효하지 않은 토큰입니다");
		}

		Long userId = jwtFactory.getClaimFromToken(token, USER_ID);
		User findUser = userRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다."));

		request.setAttribute("user", findUser);

		return true;
	}

	private String parseToken(String authorization) {
		String token;
		try {
			validateAuthorizationHeader(authorization);
			token = authorization.substring(TOKEN_TYPE.length());
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("유효하지 않은 토큰입니다");
		}
		return token;
	}

	private void validateAuthorizationHeader(String authorization) {
		if (authorization == null || !authorization.startsWith(TOKEN_TYPE)) {
			throw new IllegalArgumentException("토큰의 형식이 잘못되었습니다.");
		}
	}
}
