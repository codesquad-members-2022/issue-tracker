package louie.hanse.issuetracker.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import louie.hanse.issuetracker.login.jwt.JwtProvider;
import louie.hanse.issuetracker.service.IssueService;
import louie.hanse.issuetracker.web.dto.issue.IssueDetailResponse;
import louie.hanse.issuetracker.web.dto.issue.IssueSaveRequest;
import louie.hanse.issuetracker.web.dto.issue.IssueSearchRequest;
import louie.hanse.issuetracker.web.dto.issue.IssueSearchResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("/issues")
@RestController
@RequiredArgsConstructor
@Slf4j
public class IssueController {
    private final IssueService issueService;
    private final JwtProvider jwtProvider;

    @PostMapping
    public void registerIssue(@RequestBody IssueSaveRequest issueSaveRequest,
        HttpServletRequest request) {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long memberId = jwtProvider.verifyAccessTokenAndDecodeMemberId(accessToken);
        issueService.register(issueSaveRequest, memberId);
    }

    @GetMapping
    public IssueSearchResponse searchIssue(IssueSearchRequest issueSearchRequest) {
        return issueService.search(issueSearchRequest);
    }

    @GetMapping("/{id}")
    public IssueDetailResponse issueDetail(@PathVariable Long id, HttpServletRequest request) {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long requestMemberId = jwtProvider.verifyAccessTokenAndDecodeMemberId(accessToken);
        return issueService.findIssue(id, requestMemberId);
    }

    @PatchMapping("/{id}")
    public void editTitle(@PathVariable Long id, @RequestBody Map<String, String> map) {
        String title = map.get("title");
        issueService.edit(id, title);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable Long id) {
        issueService.delete(id);
    }
}
