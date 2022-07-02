package com.team33.backend.common.login.configuration;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClientRegistration {
    private final String redirectUri;
    private final String tokenUrl;
    private final String userInfoUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;
    private final String loginPage;

    public static ClientRegistration bind(OauthClientProperties.Registration registration, OauthClientProperties.Provider provider) {
        return new ClientRegistration(registration.getRedirectUri(), provider.getTokenUri(), provider.getUserInfoUri(),
                registration.getClientId(), registration.getClientSecret(), registration.getRedirectUri(), registration.getLoginPage());
    }

    public ClientRegistration(String redirectUri, String tokenUrl, String userInfoUrl, String clientId, String clientSecret, String redirectUrl, String loginPage) {
        this.redirectUri = redirectUri;
        this.tokenUrl = tokenUrl;
        this.userInfoUrl = userInfoUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
        this.loginPage = loginPage;
    }
}
