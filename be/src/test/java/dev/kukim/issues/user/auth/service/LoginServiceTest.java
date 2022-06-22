package dev.kukim.issues.user.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import dev.kukim.issues.user.auth.controller.response.GithubUserResponse;
import dev.kukim.issues.user.auth.controller.response.LoginResponse;
import dev.kukim.issues.user.user.domain.User;
import dev.kukim.issues.user.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("LoginServiceTest 클래스")
@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

	LoginService loginService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginTokenGenerator loginTokenGenerator;

	@Mock
	GithubOAuth githubOAuth;

	User user;
	GithubUserResponse githubUserResponse;

	@BeforeEach
	void initObject() {
		// 테스트 유저 폼 생성
		this.user = User.builder()
			.name("kukim")
			.email("test@test.com")
			.avatarUrl("http://test.com/kukim.jpg")
			.thirdPartyId(123456L)
			.build();

		// 외부 시스템(Github OAuth) 모킹과 서비스 생성
		this.githubOAuth = Mockito.mock(GithubOAuth.class);
		loginService = new LoginService(userRepository, loginTokenGenerator, githubOAuth);

		// 외부 시스템(Github OAuth) 응답 폼 생성
		this.githubUserResponse = new GithubUserResponse();
		this.githubUserResponse.setId(123456L);
		this.githubUserResponse.setName("kukim");
		this.githubUserResponse.setAvatarUrl("http://test.com/kukim.jpg");
		this.githubUserResponse.setEmail("test@test.com");
	}

	@BeforeEach
	void cleanUpDB() {
		// DB cleanup
		userRepository.deleteAll();
	}

	@Test
	@DisplayName("특정 유저의 github code가 전달된 경우, 이미 가입된 유저라면, 유저를 조회하고 엑세스 토큰을 발급한다.")
	void 이미_가입된_github_서비스_가입_사용자_조회와_토큰_발급() {
		// given : 한 명의 유저를 저장하고, 외부 시스템 GithubOAuth mock
		user.setName("유저1");
		userRepository.save(user);
		githubUserResponse.setName("유저1");
		given(githubOAuth.getGithubUserResponse("gho_code1234"))
			.willReturn(githubUserResponse);

		// when
		LoginResponse loginResponse = loginService.createToken("gho_code1234");

		// then
		assertThat(loginResponse.getName()).isEqualTo("유저1");
		assertThat(loginResponse.getAccessToken()).isNotBlank();
	}

	@Test
	@DisplayName("특정 유저의 github code가 전달된 경우, 가입되지않은 유저라면, 유저를 생성하고 엑세스 토큰을 발급한다.")
	void 신규_가입한_github_서비스_사용자_생성과_토큰_발급() {
		// given : 외부 시스템 GithubOAuth mock
		githubUserResponse.setName("신규유저1");
		when(githubOAuth.getGithubUserResponse("gho_code1234"))
			.thenReturn(githubUserResponse);

		// when
		LoginResponse loginResponse = loginService.createToken("gho_code1234");

		// then
		assertThat(loginResponse.getName()).isEqualTo("신규유저1");
		assertThat(loginResponse.getAccessToken()).isNotBlank();
	}

}
