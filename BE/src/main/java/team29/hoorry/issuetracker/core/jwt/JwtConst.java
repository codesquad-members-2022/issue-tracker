package team29.hoorry.issuetracker.core.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Duration;
import javax.crypto.SecretKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtConst {

	private static final long DAYS_OF_WEEK = 7;
	private static final long HOURS_OF_DAY = 24;
	private static final long MINUTES_OF_HOUR = 60;
	private static final long SECONDS_OF_MINUTE = 60;
	
	public static final SecretKey SECRET_KET = Keys.hmacShaKeyFor(Decoders.BASE64.decode(System.getenv("JWT_SECRET_KEY")));
	public static final long ACCESS_TOKEN_EXPIRED_TIME = Duration.ofHours(1).toMillis();
	public static final long REFRESH_TOKEN_EXPIRED_MILLIS_TIME = Duration.ofDays(7).toMillis();
	public static final long REFRESH_TOKEN_EXPIRED_SECONDS_TIME = DAYS_OF_WEEK * HOURS_OF_DAY * MINUTES_OF_HOUR * SECONDS_OF_MINUTE;
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer";
}
