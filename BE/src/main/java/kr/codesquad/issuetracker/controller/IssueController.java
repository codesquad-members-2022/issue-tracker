package kr.codesquad.issuetracker.controller;

import kr.codesquad.issuetracker.dto.IssueResponse;
import kr.codesquad.issuetracker.dto.IssueStatusRequest;
import kr.codesquad.issuetracker.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/issues")
    public List<IssueResponse> getOpenedIssue(){
        return issueService.getOpenedIssue();
    }

    @PatchMapping("/issues")
    public ResponseEntity editIssueStatus(@RequestBody IssueStatusRequest issueStatusRequest,
                                          @RequestParam Boolean status) {
        return issueService.editStatus(issueStatusRequest, status);
    }
}
