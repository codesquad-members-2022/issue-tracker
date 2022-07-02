package com.team33.backend.common.login.configuration;


import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.HashMap;
import java.util.Map;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "oauth.client")
public class OauthClientProperties {

    private final Map<String, Registration> registration = new HashMap<>();

    private final Map<String, Provider> provider = new HashMap<>();

    public OauthClientProperties() {
    }

    public Map<String, Registration> getRegistration() {
        return registration;
    }

    @Getter
    public static class Registration {
        private final String clientId;
        private final String clientSecret;
        private final String redirectUri;
        private final String loginPage;

        public Registration(String clientId, String clientSecret, String redirectUri, String loginPage) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.redirectUri = redirectUri;
            this.loginPage = loginPage;
        }
    }

    @Getter
    public static class Provider {
        private final String tokenUri;
        private final String userInfoUri;
        private String userNameAttribute;
        private final String userAuthrozationUri;
        private String version;

        public Provider(String tokenUri, String userInfoUri, String userNameAttribute, String userAuthrozationUri, String version) {
            this.tokenUri = tokenUri;
            this.userInfoUri = userInfoUri;
            this.userNameAttribute = userNameAttribute;
            this.userAuthrozationUri = userAuthrozationUri;
        }
    }

}
