package codesquad.issuetracker.config;

import codesquad.issuetracker.jwt.AccessTokenProvider;
import codesquad.issuetracker.jwt.RefreshTokenProvider;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    private static final String SECRET = System.getenv("JWT_SECRET");
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final long MINUTE = 60 * 1000;
    private static final long ACCESS_TOKEN_DURATION = 2 * MINUTE; // access token 유효시간 : 1분
    private static final long REFRESH_TOKEN_DURATION = 3 * MINUTE; // refresh token 유효시간 : 2분

    @Bean
    public AccessTokenProvider accessTokenProvider() {
        return new AccessTokenProvider(SECRET, SIGNATURE_ALGORITHM, ACCESS_TOKEN_DURATION);
    }

    @Bean
    public RefreshTokenProvider refreshTokenProvider() {
        return new RefreshTokenProvider(SECRET, SIGNATURE_ALGORITHM, REFRESH_TOKEN_DURATION);
    }
}
