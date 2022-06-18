package kr.codesquad.issuetraker.controller;

import kr.codesquad.issuetraker.dto.NewIssueResponseDto;
import kr.codesquad.issuetraker.dto.IssueDetailResponseDto;
import kr.codesquad.issuetraker.dto.IssueListResponseDto;
import kr.codesquad.issuetraker.dto.NewIssueRequestDto;
import kr.codesquad.issuetraker.sevice.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issues")
public class IssueController {
    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<List<IssueListResponseDto>> loadAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @PostMapping
    public ResponseEntity<NewIssueResponseDto> createIssue(@RequestBody NewIssueRequestDto requestDto) {
        return ResponseEntity.ok(issueService.createIssue(requestDto));
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<IssueDetailResponseDto> loadIssueDetails(@PathVariable Long issueId) {
        return ResponseEntity.ok(issueService.getIssueDetail(issueId));
    }

}
