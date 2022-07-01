package team25.issuetracker.service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import team25.issuetracker.controller.ResponseTokenMessage;
import team25.issuetracker.domain.OauthRefreshToken;
import team25.issuetracker.repository.InMemoryProviderRepository;
import team25.issuetracker.JwtTokenProvider;
import team25.issuetracker.controller.LoginResponse;
import team25.issuetracker.domain.Member;
import team25.issuetracker.repository.MemberRepository;
import team25.issuetracker.OauthAttributes;
import team25.issuetracker.OauthProvider;
import team25.issuetracker.OauthTokenResponse;
import team25.issuetracker.UserProfile;
import team25.issuetracker.repository.RefreshTokenRepository;

@Service
public class OauthService {

	private final InMemoryProviderRepository inMemoryProviderRepository;
	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final RefreshTokenRepository refreshTokenRepository;

	public OauthService(InMemoryProviderRepository inMemoryProviderRepository,
		MemberRepository memberRepository,
		JwtTokenProvider jwtTokenProvider,
		RefreshTokenRepository refreshTokenRepository) {
		this.inMemoryProviderRepository = inMemoryProviderRepository;
		this.memberRepository = memberRepository;
		this.jwtTokenProvider = jwtTokenProvider;
		this.refreshTokenRepository = refreshTokenRepository;
	}

	public LoginResponse login(String providerName, String code) {
		// 프론트에서 넘어온 provider 이름을 통해 InMemoryProviderRepository에서 OauthProvider 가져오기
		OauthProvider provider = inMemoryProviderRepository.findByProviderName(providerName);

		// access token 가져오기
		OauthTokenResponse tokenResponse = getToken(code, provider);

		// 유저 정보 가져오기
		UserProfile userProfile = getUserProfile(providerName, tokenResponse, provider);

		// 유저 DB에 저장
		Member member = saveOrUpdate(userProfile);

		String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(member.getId()));
		String refreshToken = jwtTokenProvider.createRefreshToken();

		// TODO 레디스에 refresh token 추가
		// redisUtil.setData(String.valueOf(member.getId()), refreshToken);

		// refresh token 저장
		OauthRefreshToken refreshTokenObj = OauthRefreshToken.builder()
			.refreshToken(refreshToken)
			.keyEmail(member.getEmail())
			.build();
		refreshTokenRepository.save(refreshTokenObj);

		return LoginResponse.builder()
			.id(member.getId())
			.name(member.getName())
			.email(member.getEmail())
			.imageUrl(member.getImageUrl())
			.role(member.getRole())
			.tokenType("Bearer")
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public Optional<OauthRefreshToken> getRefreshToken(String refreshToken) {

		return refreshTokenRepository.findByRefreshToken(refreshToken);
	}

	public ResponseTokenMessage validateRefreshToken(String refreshToken) {
		OauthRefreshToken refreshTokenObj = getRefreshToken(refreshToken).get();
		String createdAccessToken = jwtTokenProvider.validateRefreshToken(refreshTokenObj);
		RefreshJson refreshJson = createRefreshJson(createdAccessToken);
		return ResponseTokenMessage.builder()
			.accessToken(refreshJson.getAccessToken())
			.refreshToken(refreshTokenObj.getRefreshToken())
			.errorType(refreshJson.getErrorType())
			.message(refreshJson.getMessage())
			.status(refreshJson.getStatus())
			.build();
	}

	public RefreshJson createRefreshJson(String createdAccessToken) {

		if (createdAccessToken == null) {
			return RefreshJson.builder()
				.accessToken("")
				.errorType(HttpStatus.FORBIDDEN)
				.status(HttpStatus.PAYMENT_REQUIRED)
				.message("Refresh 토큰이 만료되었습니다. 로그인이 필요합니다.")
				.build();
		}
		return RefreshJson.builder()
			.accessToken(createdAccessToken)
			.status(HttpStatus.OK)
			.message("Refresh 토큰을 통한 Access Token 생성이 완료되었습니다.")
			.build();
	}

	private Member saveOrUpdate(UserProfile userProfile) {
		Member member = memberRepository.findByOauthId(userProfile.getOauthId())
			.map(entity -> entity.update(
				userProfile.getEmail(), userProfile.getName(), userProfile.getImageUrl()))
			.orElseGet(userProfile::toMember);
		return memberRepository.save(member);
	}

	private OauthTokenResponse getToken(String code, OauthProvider provider) {
		return WebClient.create()
			.post()
			.uri(provider.getTokenUrl())
			.headers(header -> {
				header.setBasicAuth(provider.getClientId(), provider.getClientSecret());
				header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
			})
			.bodyValue(tokenRequest(code, provider))
			.retrieve()
			.bodyToMono(OauthTokenResponse.class)
			.block();
	}

	private MultiValueMap<String, String> tokenRequest(String code, OauthProvider provider) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("code", code);
		formData.add("grant_type", "authorization_code");
		formData.add("redirect_uri", provider.getRedirectUrl());
		return formData;
	}

	private UserProfile getUserProfile(String providerName, OauthTokenResponse tokenResponse,
		OauthProvider provider) {
		Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponse);
		return OauthAttributes.extract(providerName, userAttributes);
	}

	// OAuth 서버에서 유저 정보 map으로 가져오기
	private Map<String, Object> getUserAttributes(OauthProvider provider,
		OauthTokenResponse tokenResponse) {
		return WebClient.create()
			.get()
			.uri(provider.getUserInfoUrl())
			.headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
			})
			.block();
	}
}
