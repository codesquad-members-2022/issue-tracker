package louie.hanse.issuetracker.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

import static javax.management.timer.Timer.ONE_HOUR;
import static javax.management.timer.Timer.ONE_WEEK;

@Component
public class JwtProvider {
    public final String issuer;
    public final Algorithm algorithm;

    public JwtProvider(JwtProperties jwtProperties) {
        this.issuer = jwtProperties.getIssuer();
        this.algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
    }

    public String createAccessToken(Long memberId) {
        Date expiresAt = Date.from(Instant.now().plusMillis(ONE_HOUR));
        return createToken("accessToken", memberId, expiresAt);
    }

    public String createRefreshToken(Long memberId) {
        Date expiresAt = Date.from(Instant.now().plusMillis(ONE_WEEK));
        return createToken("refreshToken", memberId, expiresAt);
    }

    private String createToken(String subject, Long memberId, Date expiresAt) {
        Date issuedAt = Date.from(Instant.now());

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withAudience(memberId.toString())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)

                .withClaim("memberId", memberId)

                .sign(algorithm);
    }
}
