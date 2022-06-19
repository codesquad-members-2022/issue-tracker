package codesquad.issuetracker.service;

import codesquad.issuetracker.domain.Member;
import codesquad.issuetracker.dto.auth.AuthMemberInformation;
import codesquad.issuetracker.dto.auth.OAuthAccessToken;
import codesquad.issuetracker.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private static final String GITHUB_AUTHORIZATION_SERVER_URL = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_API_ACCEPT_HEADER = "application/vnd.github.v3+json";
    private static final String USER_SCOPE = "user";
    private static final String GITHUB_RESOURCE_SERVER_USER_API_URL = "https://api.github.com/" + USER_SCOPE;

    private final MemberRepository memberRepository;

    @Transactional
    public Long authorizeForThirdParty(String code) {
        OAuthAccessToken token = getAccessTokenFromAuthServer(code);
        AuthMemberInformation authMemberInformation = getAuthMemberInfoFromResourceServer(token);

        return saveMember(authMemberInformation);
    }

    private OAuthAccessToken getAccessTokenFromAuthServer(String code) {
        MultiValueMap<String, String> requestHeader = getRequestHeaderForAuth();
        MultiValueMap<String, String> requestPayload = getRequestPayloadForAuth(code);
        HttpEntity<?> request = new HttpEntity<>(requestPayload, requestHeader);

        ResponseEntity<?> response = new RestTemplate().postForEntity(
            GITHUB_AUTHORIZATION_SERVER_URL, request, OAuthAccessToken.class);

        return (OAuthAccessToken) response.getBody();
    }

    private MultiValueMap<String, String> getRequestHeaderForAuth() {
        MultiValueMap<String, String> requestHeader = new LinkedMultiValueMap<>();
        requestHeader.set("Accept", "application/json");
        return requestHeader;
    }

    private MultiValueMap<String, String> getRequestPayloadForAuth(String code) {
        MultiValueMap<String, String> requestPayload = new LinkedMultiValueMap<>();
        requestPayload.set("client_id", System.getenv("CLIENT_ID"));
        requestPayload.set("client_secret", System.getenv("CLIENT_SECRET"));
        requestPayload.set("code", code);
        return requestPayload;
    }

    private AuthMemberInformation getAuthMemberInfoFromResourceServer(OAuthAccessToken token) {
        HttpEntity<?> request = new HttpEntity<>(getRequestHeaderForResource(token));
        ResponseEntity<AuthMemberInformation> response = new RestTemplate().exchange(
            GITHUB_RESOURCE_SERVER_USER_API_URL,
            HttpMethod.GET,
            request,
            AuthMemberInformation.class
        );

        return response.getBody();
    }

    private MultiValueMap<String, String> getRequestHeaderForResource(OAuthAccessToken token) {
        MultiValueMap<String, String> requestHeader = new LinkedMultiValueMap<>();
        requestHeader.set("Accept", GITHUB_API_ACCEPT_HEADER);
        requestHeader.set("Authorization", token.getAuthorizationValue());
        return requestHeader;
    }

    private Long saveMember(AuthMemberInformation authMemberInformation) {
        Member member = memberRepository.findByGithubIdentity(authMemberInformation.getIdentity());

        if (member == null) {
            return memberRepository.save(authMemberInformation);
        }

        return member.getId();
    }
}
