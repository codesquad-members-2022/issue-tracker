package kr.codesquad.issuetracker.web.controller;

import kr.codesquad.issuetracker.service.UserService;
import kr.codesquad.issuetracker.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;

	@GetMapping("/login/oauth2/code/github")
	public UserResponseDto login(@RequestParam String code) {
		log.debug("로그인 요청");
		return userService.login(code);
	}
}
