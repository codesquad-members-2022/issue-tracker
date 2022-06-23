package codesquad.issuetracker.dto.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuthAccessToken {

    private String accessToken;
    private String scope;
    private String tokenType;

    public String getAuthorizationValue() {
        return this.tokenType + " " + this.accessToken;
    }
}
