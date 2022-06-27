package louie.hanse.issuetracker.web.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.login.jwt.JwtProvider;
import louie.hanse.issuetracker.service.IssueService;
import louie.hanse.issuetracker.web.dto.IssueSaveRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/issues")
@RestController
@RequiredArgsConstructor
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
}
