package com.team33.backend.issue.controller;

import com.team33.backend.common.login.token.jwt.JwtTokenProvider;
import com.team33.backend.issue.controller.dto.issue.IssueListRequest;
import com.team33.backend.issue.controller.dto.issue.IssueListResponse;
import com.team33.backend.issue.domain.IssueStatus;
import com.team33.backend.issue.service.IssueService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issuegroup")
public class IssueController {

    private final JwtTokenProvider provider = new JwtTokenProvider();
    private final IssueService issueService;

    @GetMapping("/{issueGroupId}/issues")
    public IssueListResponse issueList(
            HttpServletRequest servletRequest,
            @PathVariable("issueGroupId") long issueGroupId,
            @PageableDefault Pageable pageable,
            @RequestParam(value = "status", defaultValue = "OPEN") String status,
            @RequestParam(value = "q", required = false) String filterQuery
    ) {
        String githubId = extractGithubIdFromAccessToken(servletRequest);

        IssueListRequest issueListRequest = new IssueListRequest(pageable, IssueStatus.parse(status), issueGroupId);

        if (isNullOrEmpty(filterQuery)) {
            return issueService.findAllIssueWithStatus(issueListRequest);
        }

        return issueService.findAllIssueWithStatusAndFilter(issueListRequest, filterQuery, githubId);
    }

    private String extractGithubIdFromAccessToken(HttpServletRequest servletRequest) {
        String authorization = servletRequest.getHeader("Authorization");
        String accessToken = authorization.split(" ")[1];

        Claims claims = provider.getClaims(accessToken);
        String githubId = claims.get("githubId", String.class);
        return githubId;
    }

    private boolean isNullOrEmpty(String filterQuery) {
        return filterQuery == null || filterQuery.isEmpty();
    }
}
