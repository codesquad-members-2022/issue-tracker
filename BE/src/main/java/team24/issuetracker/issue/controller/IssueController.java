package team24.issuetracker.issue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.issue.domain.dto.IssueListResponse;
import team24.issuetracker.issue.application.IssueService;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

	private final IssueService issueService;

	@GetMapping("created-by/{id}")
	public List<IssueListResponse> findByWriter(@PathVariable("id") Long id) {
		return issueService.findIssuesCreatedByMe(id);
	}

	@GetMapping("assigned-to/{id}")
	public List<IssueListResponse> findByAssignee(@PathVariable("id") Long id) {
		return issueService.findIssuesAssignedToMe(id);
	}

	@GetMapping("commented-by/{id}")
	public List<IssueListResponse> findByComment(@PathVariable("id") Long id) {
		return issueService.findIssuesCommentedByMe(id);
	}

	@GetMapping
	public List<IssueListResponse> findClosedIssues(@RequestParam("closed") Boolean isClosed) {
		return issueService.findByIsClosed(isClosed);
	}
}
