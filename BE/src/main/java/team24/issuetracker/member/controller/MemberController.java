package team24.issuetracker.member.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team24.issuetracker.member.domain.dto.MemberResponse;
import team24.issuetracker.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/assignees")
	public List<MemberResponse> getAssignees() {
		return memberService.findMemberResponse();
	}
}
