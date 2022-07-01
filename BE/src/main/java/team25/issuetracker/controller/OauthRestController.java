package team25.issuetracker.controller;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team25.issuetracker.service.OauthService;

@Slf4j
@RestController
public class OauthRestController {

	private final OauthService oauthService;

	public OauthRestController(OauthService oauthService) {
		this.oauthService = oauthService;
	}

	/**
	 * @param provider API 요청시 해당되는 파티 입력
	 * @param code     프론트에서 지니고 있는 Authorization Code 받기
	 */
	@GetMapping("/login/oauth/{provider}")
	public ResponseEntity<LoginResponse> login(@Valid @PathVariable String provider,
		@Valid @RequestParam String code) {
		LoginResponse loginResponse = oauthService.login(provider, code);

		log.info("login email: " + loginResponse.getEmail());
		return ResponseEntity.ok().body(loginResponse);
	}

	@PostMapping("/refresh")
	public ResponseEntity<ResponseTokenMessage> validateRefreshToken(@Valid @RequestBody
		RequestRefreshToken requestRefreshToken) {
		String refreshToken = requestRefreshToken.getRefreshToken();

		log.info("토큰 재발급 api 실행");
		ResponseTokenMessage responseTokenMessage = oauthService.validateRefreshToken(refreshToken);

		if (responseTokenMessage.getStatus().equals(HttpStatus.PAYMENT_REQUIRED)) {
			log.info("RefreshController - Refresh Token이 만료.");
			return new ResponseEntity(responseTokenMessage,
				HttpStatus.UNAUTHORIZED);
		}

		log.info("RefreshController - Refresh Token이 유효.");
		return new ResponseEntity(responseTokenMessage, HttpStatus.OK);
	}
}
