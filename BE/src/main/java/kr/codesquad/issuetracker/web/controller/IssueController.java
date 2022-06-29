package kr.codesquad.issuetracker.web.controller;

import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.service.IssueService;
import kr.codesquad.issuetracker.service.IssueSearchService;
import kr.codesquad.issuetracker.web.dto.issue.IssueAddRequestDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueModifyRequestDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/issue")
@RestController
public class IssueController {

	private final IssueSearchService issueSearchService;

	private final IssueService issueService;

	@GetMapping
	public IssueResponseDto list(@RequestParam Status status) {
		return issueSearchService.list(status);
	}

	@PostMapping("/modify")
	public IssueResponseDto modifyStatus(@RequestBody IssueModifyRequestDto dto) {
		issueService.statusModify(dto);
		return issueSearchService.list(dto.getStatus());
	}

	@PostMapping
	public void add(@RequestBody IssueAddRequestDto dto) {
		issueService.add(dto);
	}

}
