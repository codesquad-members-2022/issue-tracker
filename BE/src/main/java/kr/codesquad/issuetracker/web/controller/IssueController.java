package kr.codesquad.issuetracker.web.controller;

import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.service.IssueService;
import kr.codesquad.issuetracker.web.dto.issue.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/issue")
@RestController
public class IssueController {

	private final IssueService issueService;

	@GetMapping
	public IssueResponseDto list(@RequestParam Status status) {
		return issueService.list(status);
	}

}
