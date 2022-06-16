package team24.issuetracker.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.web.dto.IssueListResponse;
import team24.issuetracker.web.service.IssueService;

@RestController
@RequiredArgsConstructor
public class IssueController {

	private final IssueService issueService;

	@GetMapping("/issues")
	public List<IssueListResponse> findIssues() {
		return issueService.findIssues();
	}

	@GetMapping("/issues/created_by/{id}")
	public List<IssueListResponse> findByWriter(@PathVariable("id") Long id) {
		return issueService.findIssuesCreatedByMe(id);
	}

	@GetMapping("/issues/assigned/{id}")
	public List<IssueListResponse> findByAssignee(@PathVariable("id") Long id) {
		return issueService.findIssuesAssignedToMe(id);
	}
}
