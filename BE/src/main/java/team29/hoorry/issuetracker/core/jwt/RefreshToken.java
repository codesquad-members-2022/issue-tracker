package team29.hoorry.issuetracker.core.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = JwtConst.REFRESH_TOKEN_EXPIRED_SECONDS_TIME)
public class RefreshToken {

	@Id
	private String token;
}
