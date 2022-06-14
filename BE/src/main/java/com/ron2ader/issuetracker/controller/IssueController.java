package com.ron2ader.issuetracker.controller;

import com.ron2ader.issuetracker.controller.issuedto.IssueCreateRequest;
import com.ron2ader.issuetracker.controller.issuedto.IssueDetailResponse;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.service.IssueService;
import com.ron2ader.issuetracker.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("issue")
public class IssueController {

    private final IssueService issueService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<IssueDetailResponse> register(HttpServletRequest httpRequest, IssueCreateRequest issueCreateRequest) {
        MemberDto memberDto = memberService.findMember(httpRequest.getAttribute("userId").toString());
        IssueDetailResponse issueDetailResponse = issueService.registerIssue(issueCreateRequest, memberDto);

        return ResponseEntity.ok(issueDetailResponse);
    }

    @PostMapping("/{issueNumber}")
    public ResponseEntity<IssueDetailResponse> showIssue(@PathVariable Long issueNumber) {
        IssueDetailResponse issueDetailResponse = issueService.findByIssueNumber(issueNumber);

        return ResponseEntity.ok(issueDetailResponse);
    }
}
