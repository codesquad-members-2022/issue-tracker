package com.ron2ader.issuetracker.controller;

import com.ron2ader.issuetracker.controller.issuedto.IssueCreateRequest;
import com.ron2ader.issuetracker.controller.issuedto.IssueDetailResponse;
import com.ron2ader.issuetracker.controller.issuedto.IssueSimpleResponse;
import com.ron2ader.issuetracker.controller.memberdto.MemberDto;
import com.ron2ader.issuetracker.service.IssueService;
import com.ron2ader.issuetracker.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{issueNumber}")
    public ResponseEntity<IssueDetailResponse> showIssue(@PathVariable Long issueNumber) {
        IssueDetailResponse issueDetailResponse = issueService.findById(issueNumber);

        return ResponseEntity.ok(issueDetailResponse);
    }

    @GetMapping
    public ResponseEntity<Page<IssueSimpleResponse>> showIssuesByOpenStatus(Pageable pageable, Boolean openStatus) {

        Page<IssueSimpleResponse> issues = issueService.findAllByOpenStatus(pageable, openStatus);

        return ResponseEntity.ok(issues);
    }
}
