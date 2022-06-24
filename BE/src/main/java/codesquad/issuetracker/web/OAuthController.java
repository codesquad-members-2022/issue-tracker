package codesquad.issuetracker.web;

import codesquad.issuetracker.service.OAuthService;
import codesquad.issuetracker.web.dto.LoginResponseDto;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthController {

	private static final String GITHUB_REDIRECT_URL = "https://github.com/login/oauth/authorize?client_id=a062f7e1bd2cefcb3157";

	private final OAuthService oAuthService;

	@GetMapping("/login/oauth")
	public ResponseEntity<Void> login() throws URISyntaxException {
		return ResponseEntity.status(HttpStatus.FOUND).location(new URI(GITHUB_REDIRECT_URL))
			.build();
	}

	@GetMapping("/login/oauth/redirect")
	public LoginResponseDto oauth(@RequestParam String code) {
		return oAuthService.login(code);
	}

}
