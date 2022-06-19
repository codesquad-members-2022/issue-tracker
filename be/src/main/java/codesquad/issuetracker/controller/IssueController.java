package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.issue.IssueCountDto;
import codesquad.issuetracker.dto.issue.IssueDtoList;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.service.IssueService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/api/issues")
    public IssueDtoList issues(IssueSearchCondition condition) {
        return issueService.getIssuesByCriteria(condition);
    }

     @GetMapping("/api/issues/count")
    public IssueCountDto count() {
        return issueService.getCountOfIssuesByStatus();
     }
}
