package codesquad.issuetracker.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

    private String message;
    private String tokenType;
    private String accessToken;
    private String refreshToken;
}
