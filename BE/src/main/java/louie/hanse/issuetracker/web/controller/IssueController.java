package louie.hanse.issuetracker.web.controller;

import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.service.IssueService;
import louie.hanse.issuetracker.web.dto.IssueSaveRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/issues")
@RestController
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @PostMapping
    public void registerIssue(@RequestBody IssueSaveRequest issueSaveRequest){
        issueService.register(issueSaveRequest);
    }
}
