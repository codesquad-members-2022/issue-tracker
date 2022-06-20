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
    public static final long ACCESS_TOKEN_DURATION = 60 * 1 * 1000; // access token 유효시간 : 1분
    public static final long REFRESH_TOKEN_DURATION = 60 * 2 * 1000; // refresh token 유효시간 : 2분

    @Bean
    public AccessTokenProvider accessTokenProvider() {
        return new AccessTokenProvider(SECRET, SIGNATURE_ALGORITHM, ACCESS_TOKEN_DURATION);
    }

    @Bean
    public RefreshTokenProvider refreshTokenProvider() {
        return new RefreshTokenProvider(SECRET, SIGNATURE_ALGORITHM, REFRESH_TOKEN_DURATION);
    }
}
