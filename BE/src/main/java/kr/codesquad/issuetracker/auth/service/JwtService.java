package kr.codesquad.issuetracker.auth.service;

import static kr.codesquad.issuetracker.auth.utils.OauthUtils.USER_ID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import kr.codesquad.issuetracker.domain.member.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

	private final String ISSUER;
	private final String SECRET_KEY;

	public JwtService(@Value("${jwt.issuer}")String ISSUER,
						@Value("${jwt.secret-key}")String SECRET_KEY) {
		this.ISSUER = ISSUER;
		this.SECRET_KEY = SECRET_KEY;
	}

	public String createToken(Member member) {
		return JWT.create()
			.withExpiresAt(new Date())
			.withClaim(USER_ID, member.getId())
			.withIssuer(ISSUER)
			.sign(Algorithm.HMAC256(SECRET_KEY));
	}
}
