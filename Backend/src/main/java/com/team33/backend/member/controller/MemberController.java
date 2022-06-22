package com.team33.backend.member.controller;

import com.team33.backend.member.controller.dto.MemberQueryResponse;
import com.team33.backend.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issuegroup")
public class MemberController {

    private final MemberQueryService memberQueryService;

    @GetMapping("/{issuegroupId}/members")
    public List<MemberQueryResponse> findIssueGroupMembers(@PathVariable Long issuegroupId) {
        return memberQueryService.findGroupMembers(issuegroupId);
    }
}
