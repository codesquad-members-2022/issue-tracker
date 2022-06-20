package codesquad.issuetracker.interceptor;

import codesquad.issuetracker.dto.ResponseMessage;
import codesquad.issuetracker.jwt.AccessTokenProvider;
import codesquad.issuetracker.jwt.Token;
import codesquad.issuetracker.jwt.TokenManager;
import codesquad.issuetracker.jwt.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AccessTokenProvider accessTokenProvider;
    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        String accessTokenString = TokenUtils.getAccessToken(request);
        Token accessToken = accessTokenProvider.convertToObject(accessTokenString);

        if (accessToken.validateExpirationOfToken() &&
            tokenManager.validateLogInStatusOfAccessToken(accessTokenString)) {
            return true;
        }

        setResponseHeader(response);
        setResponseBody(response);

        return false;
    }

    private void setResponseHeader(HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
    }

    private void setResponseBody(HttpServletResponse response) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.UNAUTHORIZED, "access 토큰이 만료되었습니다.");
        String stringOfResponseMessage = new ObjectMapper().writeValueAsString(responseMessage);
        response.getWriter().write(stringOfResponseMessage);
    }
}
