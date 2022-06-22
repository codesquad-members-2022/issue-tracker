package dev.kukim.issues.user.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.time.Instant;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginTokenGenerator {

	private static final long HALF_HOUR = 30L;
	private static final int ONE_MINUTE = 60;

	@Value("${jwt.secret}")
	private String jwtSecret;
	private SecretKey secretKey;


	public String createToken(Long userId) {
		return generateToken(userId,
			Date.from(Instant.now().plusSeconds(ONE_MINUTE * HALF_HOUR)));
	}

	private String generateToken(Long userId, Date plusMinutes) {
		secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder()
			.setHeader(Map.of(
				"typ", "JWT",
				"alg", "HS256",
				"regDate", System.currentTimeMillis()))
			.setClaims(Map.of("userId", userId))
			.setExpiration(plusMinutes)
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact();
	}

	public boolean validateToken(String token) {
		try {
			getClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public Jws<Claims> getClaims(String token) {
		secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
		return Jwts.parserBuilder()
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token);
	}

}
