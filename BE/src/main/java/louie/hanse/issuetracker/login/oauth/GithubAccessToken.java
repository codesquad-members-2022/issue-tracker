package louie.hanse.issuetracker.login.oauth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubAccessToken {
    private String accessToken;
    private String tokenType;
    private String scope;

    public String getAuthorizationValue() {
        return "Bearer " + accessToken;
    }
}
