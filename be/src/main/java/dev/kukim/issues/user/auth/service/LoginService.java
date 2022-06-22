package dev.kukim.issues.user.auth.service;

import dev.kukim.issues.user.auth.controller.response.GithubUserResponse;
import dev.kukim.issues.user.auth.controller.response.LoginResponse;
import dev.kukim.issues.user.auth.mapper.LoginResponseMapper;
import dev.kukim.issues.user.user.domain.User;
import dev.kukim.issues.user.user.domain.repository.UserRepository;
import dev.kukim.issues.user.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final UserRepository userRepository;
	private final LoginTokenGenerator loginTokenGenerator;
	private final GithubOAuth githubOAuth;

	public LoginResponse createToken(String code) {
		GithubUserResponse githubUserResponse = githubOAuth.getGithubUserResponse(code);

		User user = findOrCreatUser(githubUserResponse);

		String accessToken = loginTokenGenerator.createToken(user.getId());

		return LoginResponseMapper.INSTANCE.map(user, accessToken);
	}

	private User findOrCreatUser(GithubUserResponse githubUserResponse) {
		Long findId = githubUserResponse.getId();

		return userRepository.findByThirdPartyId(findId).orElseGet(() ->
			userRepository.save(UserMapper.INSTANCE.map(githubUserResponse)));
	}

}
