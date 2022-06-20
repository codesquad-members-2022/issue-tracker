package kr.codesquad.issuetracker.auth;

import io.jsonwebtoken.Claims;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.issuetracker.auth.exception.AuthHeaderNotFoundException;
import kr.codesquad.issuetracker.auth.exception.IllegalJwtFormatException;
import kr.codesquad.issuetracker.auth.jwt.JWTHandler;
import kr.codesquad.issuetracker.core.user.User;
import kr.codesquad.issuetracker.core.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final JWTHandler jwtHandler;
    private final UserRepository userRepository;

    private static final String BEARER = "Bearer";
    private static final String USER_ID = "userId";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        String userId = extractUserId(request);
        User user = userRepository.findByGitHubId(userId)
                .orElseThrow(() -> new NoSuchElementException());

        request.setAttribute(USER_ID, user.getUserId());

        return true;
    }

    private String extractUserId(HttpServletRequest request)  {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        verifyHeader(authHeader);

        Claims claims = jwtHandler.decodeJwt(authHeader.split(" ")[1]);
        return claims.get(USER_ID, String.class);
    }


    private void verifyHeader(String authorization) {
        if (authorization.trim().isEmpty() || authorization == null) {
            throw new AuthHeaderNotFoundException();
        }
        if (!authorization.startsWith(BEARER)) {
            throw new IllegalJwtFormatException("Authorization 헤더는 'Bearer'로 시작해야 합니다.");
        }
    }

}
