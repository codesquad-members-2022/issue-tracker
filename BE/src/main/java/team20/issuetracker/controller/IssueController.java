package team20.issuetracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team20.issuetracker.service.dto.request.RequestSaveIssueDto;
import team20.issuetracker.service.IssueService;

@RequiredArgsConstructor
@RestController
public class IssueController {

    private final IssueService issueService;

    @PostMapping("/issues/write")
    public ResponseEntity<Long> save(@RequestBody RequestSaveIssueDto requestSaveIssueDto) {

        Long issueId = issueService.save(requestSaveIssueDto);
//        URI location = URI.create("/zz");
        return ResponseEntity.ok(issueId);

//        return ResponseEntity.created(location).body(issueId);
    }
}
