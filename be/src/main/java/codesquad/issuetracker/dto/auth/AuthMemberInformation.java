package codesquad.issuetracker.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AuthMemberInformation {

    @JsonProperty("login")
    private String identity;
    @JsonProperty("avatar_url")
    private String profileUrl;
    @JsonProperty
    private String email;
    @JsonProperty
    private String name;
}
