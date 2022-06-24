package codesquad.issuetracker.service;

import codesquad.issuetracker.domain.user.User;
import codesquad.issuetracker.domain.user.UserRepository;
import codesquad.issuetracker.web.dto.GithubAccessToken;
import codesquad.issuetracker.web.dto.GithubTokenRequestDto;
import codesquad.issuetracker.web.dto.GithubUserInformation;
import codesquad.issuetracker.web.dto.LoginResponseDto;
import codesquad.issuetracker.web.jwt.JwtFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class OAuthService {

	private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
	private static final String GITHUB_RESOURCE_URL = "https://api.github.com/user";

	private final String clientId;
	private final String clientSecret;
	private final UserRepository userRepository;
	private final JwtFactory jwtFactory;

	public OAuthService(
		@Value("${oauth.github.client_id}") String clientId,
		@Value("${oauth.github.client_secret}") String clientSecret,
		UserRepository userRepository,
		JwtFactory jwtFactory) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.userRepository = userRepository;
		this.jwtFactory = jwtFactory;
	}

	public LoginResponseDto login(String code) {
		GithubTokenRequestDto githubTokenRequestDto = new GithubTokenRequestDto(clientId,
			clientSecret, code);

		GithubAccessToken githubAccessToken = requestAccessToken(githubTokenRequestDto);

		GithubUserInformation githubUserInformation = requestUserInformation(githubAccessToken);

		User user = userRepository.findByLoginId(githubUserInformation.getUserId())
			.orElseGet(() -> {

				User uzer = User.builder()
					.loginId(githubUserInformation.getUserId())
					.name(githubUserInformation.getUsername())
					//TODO 비밀번호 암호화 해야함
					.password(githubUserInformation.getPassword())
					.image(githubUserInformation.getProfileImage())
					.build();

				userRepository.save(uzer);
				return uzer;
			});

		String accessToken = jwtFactory.createAccessToken(user.getId());
		String refreshToken = jwtFactory.createRefreshToken(user.getId());
		return new LoginResponseDto(accessToken, refreshToken);
	}

	private GithubAccessToken requestAccessToken(GithubTokenRequestDto githubTokenRequestDto) {
		return WebClient
			.builder()
			.baseUrl(GITHUB_ACCESS_TOKEN_URL)
			.build()
			.post()
			.bodyValue(githubTokenRequestDto)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(GithubAccessToken.class)
			.block();
	}

	private GithubUserInformation requestUserInformation(GithubAccessToken githubAccessToken) {
		String tokenString = createTokenString(githubAccessToken);

		return WebClient.builder()
			.baseUrl(GITHUB_RESOURCE_URL)
			.build()
			.get()
			.header("Authorization", tokenString)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(GithubUserInformation.class)
			.block();
	}

	private String createTokenString(GithubAccessToken githubAccessToken) {
		return "token " + githubAccessToken.getAccessToken();
	}

}
