package codesquad.issuetracker.controller;

import codesquad.issuetracker.dto.ResponseMessage;
import codesquad.issuetracker.dto.issue.IssueDtos;
import codesquad.issuetracker.dto.issue.IssueSearchCondition;
import codesquad.issuetracker.dto.issue.IssueStatusUpdateForm;
import codesquad.issuetracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/api/issues")
    public IssueDtos issues(IssueSearchCondition condition) {
        return issueService.getIssuesByCriteria(condition);
    }

    @PatchMapping("/api/issues/status/update")
    public ResponseMessage update(@RequestBody IssueStatusUpdateForm updateForm) {
        issueService.updateStatusByIssueId(updateForm);
        return new ResponseMessage(HttpStatus.OK, "이슈의 상태 변경이 정상적으로 처리되었습니다.");
    }
}
