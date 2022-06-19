package louie.hanse.issuetracker.oauth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@RequiredArgsConstructor
@Getter
public class GithubAccessTokenRequest {
    private final String clientId;
    private final String clientSecret;
    private final String code;
}
