package louie.hanse.issuetracker;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import louie.hanse.issuetracker.jwt.JwtProperties;
import louie.hanse.issuetracker.jwt.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
class IssueTrackerApplicationTests {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    JwtProperties jwtProperties;

    @Test
    void contextLoads() {
        Date expiresAt = Date.from(Instant.now().minusSeconds(12));

        String jwt = JWT.create()
                .withIssuer(jwtProperties.getIssuer())
                .withExpiresAt(expiresAt)
                .withSubject("accessToken")
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));

        jwtProvider.verifyAccessToken(jwt);
    }

}
