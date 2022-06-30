package team29.hoorry.issuetracker.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import team29.hoorry.issuetracker.core.exception.EmptyTokenException;
import team29.hoorry.issuetracker.core.exception.ExceptionMessage;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtParser {

	public static <T> T parseClaim(String authorizationHeader, String claimName, Class<T> requiredType)
		throws JwtException, IllegalArgumentException {
		String token = extractBearerToken(authorizationHeader);
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(JwtConst.SECRET_KET)
			.build()
			.parseClaimsJws(token)
			.getBody();

		return claims.get(claimName, requiredType);
	}

	public static String extractBearerToken(String authorizationHeader) {
		if (isValidBearerToken(authorizationHeader)) {
			return authorizationHeader.split(" ")[1];
		}
		throw new EmptyTokenException(ExceptionMessage.NO_TOKEN_MESSAGE);
	}

	private static boolean isValidBearerToken(String bearerAuthorization) {
		String[] splitedAuthorization = bearerAuthorization.split(" ");
		return splitedAuthorization.length > 1 && splitedAuthorization[0].equalsIgnoreCase(JwtConst.BEARER);
	}
}
