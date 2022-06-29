package team24.issuetracker.issue.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.issue.application.IssueService;
import team24.issuetracker.issue.domain.dto.IssueListResponse;
import team24.issuetracker.issue.domain.dto.IssueRequest;

@RestController
@RequiredArgsConstructor
public class IssueController {

	private final IssueService issueService;

	@PostMapping("issue/new")
	public void writeIssue(@RequestBody IssueRequest issueRequest) {
		issueService.add(issueRequest);
	}

	@GetMapping("issues/created-by/{id}")
	public List<IssueListResponse> getByWriter(@PathVariable("id") Long id) {
		return issueService.findByWriter(id);
	}

	@GetMapping("issues/assigned-to/{id}")
	public List<IssueListResponse> getByAssignee(@PathVariable("id") Long id) {
		return issueService.findByAssignee(id);
	}

	@GetMapping("issues/commented-by/{id}")
	public List<IssueListResponse> getByComment(@PathVariable("id") Long id) {
		return issueService.findByCommenter(id);
	}

	@GetMapping("issues")
	public List<IssueListResponse> getByState(@RequestParam("closed") Boolean isClosed) {
		return issueService.findByState(isClosed);
	}

	@PutMapping("issue/change-state/{id}")
	public void changeState(@PathVariable("id") Long id) {
		issueService.updateState(id);
	}
}
