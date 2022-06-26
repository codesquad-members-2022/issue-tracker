package team24.issuetracker.oauth.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.oauth.dto.GitHubAccessToken;
import team24.issuetracker.oauth.dto.GitHubAccessTokenRequest;
import team24.issuetracker.oauth.dto.GitHubUser;
import team24.issuetracker.oauth.properties.OAuthProperties;

@Service
@RequiredArgsConstructor
public class OAuthService {

	private final OAuthProperties oAuthProperties;

	public GitHubAccessToken getAccessToken(String code) {
		GitHubAccessTokenRequest accessTokenRequest = new GitHubAccessTokenRequest(oAuthProperties, code);

		return WebClient.create().post()
			.uri(oAuthProperties.getAccessTokenUri())
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(accessTokenRequest)
			.retrieve()
			.bodyToMono(GitHubAccessToken.class)
			.block();
	}

	public GitHubUser getUserInfo(GitHubAccessToken gitHubAccessToken) {
		return WebClient.create().get()
			.uri(oAuthProperties.getUserInfoUri())
			.accept(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, gitHubAccessToken.fullInfo())
			.retrieve()
			.bodyToMono(GitHubUser.class)
			.block();
	}
}
