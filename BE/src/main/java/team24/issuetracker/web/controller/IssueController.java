package team24.issuetracker.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.web.dto.IssueListResponsse;
import team24.issuetracker.web.service.IssueService;

@RestController
@RequiredArgsConstructor
public class IssueController {

	private final IssueService issueService;

	@GetMapping("/issues")
	public List<IssueListResponsse> findIssues() {
		return issueService.findIssues();
	}
}
