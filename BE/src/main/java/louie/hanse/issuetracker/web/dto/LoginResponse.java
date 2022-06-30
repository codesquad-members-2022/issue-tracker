package louie.hanse.issuetracker.web.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String avatarImageUrl;

    public LoginResponse(String accessToken, String refreshToken, String avatarImageUrl) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.avatarImageUrl = avatarImageUrl;
    }
}
