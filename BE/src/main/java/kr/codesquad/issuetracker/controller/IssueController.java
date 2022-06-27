package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.dto.IssueResponse;
import kr.codesquad.issuetracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/issues")
    public List<IssueResponse> getOpenedIssue(){
        return issueService.getOpenedIssue();
    }
}
