package louie.hanse.issuetracker.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

import static javax.management.timer.Timer.ONE_HOUR;

@Component
public class JwtProvider {
    public final String issuer;
    public final Algorithm algorithm;

    public JwtProvider(JwtProperties jwtProperties) {
        this.issuer = jwtProperties.getIssuer();
        this.algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
    }

    public String createAccessToken(Long memberId){
        Date issuedAt = Date.from(Instant.now());
        Date expiresAt = Date.from(Instant.now().plusMillis(ONE_HOUR));

        return JWT.create()
                .withIssuer(issuer)
                .withSubject("accessToken")
                .withAudience(memberId.toString())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)

                .withClaim("memberId", memberId)

                .sign(algorithm);
    }
}
