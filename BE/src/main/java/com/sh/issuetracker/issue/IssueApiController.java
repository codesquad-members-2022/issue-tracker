package com.sh.issuetracker.issue;

import com.sh.issuetracker.exception.InvalidSearchParamException;
import com.sh.issuetracker.issue.dto.IssueRequest;
import com.sh.issuetracker.issue.dto.IssueResponse;
import com.sh.issuetracker.issue.search.IssueSearchRequest;
import com.sh.issuetracker.user.AuthUser;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	//is:open+assignee:sally+author:sally
	@GetMapping(value = "/search")
	public ResponseEntity<List<IssueResponse.Row>> search(IssueSearchRequest request) {
		List<IssueResponse.Row> response = issueService.search(AuthUser.of(), request);
		return ResponseEntity.ok().body(response);
	}

	/**
	 * 검색키워드 불일치시 빈 리스트 반환합니다.
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(InvalidSearchParamException.class)
	public ResponseEntity<List<String>> invalidSearchParamException(
		InvalidSearchParamException exception) {
		log.error(exception.getMessage());
		return ResponseEntity.status(HttpStatus.OK).body(List.of());

	}
}
