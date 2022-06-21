package be.codesquad.issuetracker.oauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenInformation {

    private String access_token;

    private String token_type;

    private String scope;

    public String getAuthorizationValue() {
        return this.token_type + " " + this.access_token;
    }
}
