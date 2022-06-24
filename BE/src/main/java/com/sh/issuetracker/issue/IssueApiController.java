package com.sh.issuetracker.issue;

import com.sh.issuetracker.issue.dto.IssueRequest;
import com.sh.issuetracker.issue.dto.IssueResponse;
import com.sh.issuetracker.user.AuthUser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/issue-tracker/issues")
public class IssueApiController {
	private final IssueService issueService;

	@GetMapping(value = {"/", "/open"})
	public ResponseEntity<List<IssueResponse.Row>> readAllWithOpen() {
		List<IssueResponse.Row> response = issueService.readAllOf(AuthUser.of(), IssueStatus.OPEN);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = {"/close"})
	public ResponseEntity<List<IssueResponse.Row>> readAllWithClose() {
		List<IssueResponse.Row> response = issueService.readAllOf(AuthUser.of(), IssueStatus.CLOSE);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = {"/created_by/me"})
	public ResponseEntity<List<IssueResponse.Row>> readAllByAuthor() {
		List<IssueResponse.Row> response = issueService.readAllOfAuthor(AuthUser.of(), IssueStatus.OPEN);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = {"/assigned/me"})
	public ResponseEntity<List<IssueResponse.Row>> readAllByAssignee() {
		List<IssueResponse.Row> response = issueService.readAllOfAuthor(AuthUser.of(), IssueStatus.OPEN);
		return ResponseEntity.ok().body(response);
	}
  
	@PatchMapping("/status")
	public void updateStatus(@Valid @RequestBody IssueRequest request) {
		issueService.update(request);
	}
}
