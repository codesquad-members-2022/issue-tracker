package kr.codesquad.issuetracker.auth;

import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.codesquad.issuetracker.auth.exception.CustomException;
import kr.codesquad.issuetracker.auth.exception.ErrorCode;
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
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

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
            throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
        }
        if (!authorization.startsWith(BEARER)) {
            throw new CustomException(ErrorCode.TOKEN_TYPE_INCORRECT);
        }
    }

}
