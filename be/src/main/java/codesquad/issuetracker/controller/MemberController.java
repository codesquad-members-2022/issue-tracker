package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.member.MemberDtoList;
import codesquad.issuetracker.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/api/members")
    public MemberDtoList members() {
        return memberService.getMembers();
    }
}
