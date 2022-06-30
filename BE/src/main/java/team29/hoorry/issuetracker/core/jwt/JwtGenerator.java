package team29.hoorry.issuetracker.core.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtGenerator {

	public static AccessToken generateAccessToken(Long memberId) {
		Date now = new Date();

		return new AccessToken(Jwts.builder()
			.setIssuedAt(now)
			.setExpiration(Date.from(Instant.now().plusMillis(JwtConst.ACCESS_TOKEN_EXPIRED_TIME)))
			.claim("memberId", memberId)
			.signWith(JwtConst.SECRET_KET, SignatureAlgorithm.HS256)
			.compact());
	}

	public static RefreshToken generateRefreshToken(Long memberId) {
		Date now = new Date();

		return new RefreshToken(Jwts.builder()
			.setIssuedAt(now)
			.setExpiration(Date.from(Instant.now().plusMillis(JwtConst.REFRESH_TOKEN_EXPIRED_MILLIS_TIME)))
			.claim("memberId", memberId)
			.signWith(JwtConst.SECRET_KET, SignatureAlgorithm.HS256)
			.compact());
	}
}
