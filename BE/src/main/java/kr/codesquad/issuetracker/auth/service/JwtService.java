package kr.codesquad.issuetracker.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import kr.codesquad.issuetracker.domain.member.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

	private static final String MEMBER_ID = "memberId";

	private static final int EXPIRATION_TIME = 60 * 144 * 10000;

	private final String ISSUER;
	private final String SECRET_KEY;

	public JwtService(@Value("${jwt.issuer}")String ISSUER,
						@Value("${jwt.secret-key}")String SECRET_KEY) {
		this.ISSUER = ISSUER;
		this.SECRET_KEY = SECRET_KEY;
	}

	public String createToken(Member member) {
		Date now = new Date();
		return JWT.create()
			.withIssuedAt(now)
			.withExpiresAt(new Date(now.getTime() + EXPIRATION_TIME))
			.withClaim(MEMBER_ID, member.getId())
			.withIssuer(ISSUER)
			.sign(Algorithm.HMAC256(SECRET_KEY));
	}

	public DecodedJWT verifyToken(String jwt) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
			.acceptExpiresAt(EXPIRATION_TIME)
			.withIssuer(ISSUER)
			.build();

		return verifier.verify(jwt);
	}

	public Long getMemberId(DecodedJWT jwt) {
		return jwt.getClaim(MEMBER_ID).asLong();
	}
}
