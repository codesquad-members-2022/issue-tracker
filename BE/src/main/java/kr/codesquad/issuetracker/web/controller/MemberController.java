package kr.codesquad.issuetracker.web.controller;

import kr.codesquad.issuetracker.service.MemberService;
import kr.codesquad.issuetracker.web.dto.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/login/oauth2/code/github")
	public MemberResponseDto login(@RequestParam String code) {
		log.debug("로그인 요청");
		return memberService.login(code);
	}
}
