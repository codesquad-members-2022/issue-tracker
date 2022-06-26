package team24.issuetracker.oauth.provider;

import static javax.management.timer.Timer.*;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import team24.issuetracker.oauth.properties.JwtProperties;


@Component
public class JwtProvider {

	private final String issuer;
	private final Algorithm algorithm;

	public JwtProvider(JwtProperties properties) {
		this.issuer = properties.getIssuer();
		this.algorithm = Algorithm.HMAC256(properties.getSecretKey());
	}

	public String createAccessToken(Long memberId) {
		Date expiresAt = Date.from(Instant.now().plusMillis(ONE_HOUR));
		return createToken("accessToken", memberId, expiresAt);
	}

	public String createRefreshToken(Long memberId) {
		Date expiresAt = Date.from(Instant.now().plusMillis(ONE_WEEK));
		return createToken("refreshToken", memberId, expiresAt);
	}

	public void verifyAccessToken(String accessToken) {
		verifyToken(accessToken, "accessToken");
	}

	public void verifyRefreshToken(String refreshToken) {
		verifyToken(refreshToken, "refreshToken");
	}

	public Long decodeMemberId(String token) {
		return JWT.decode(token)
			.getClaim("memberId")
			.asLong();
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

	private void verifyToken(String token, String subject) {
		JWT.require(algorithm)
			.withIssuer(issuer)
			.withSubject(subject)
			.build()
			.verify(token);
	}
}
