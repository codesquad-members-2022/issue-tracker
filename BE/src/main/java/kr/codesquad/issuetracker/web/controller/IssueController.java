package kr.codesquad.issuetracker.web.controller;

import java.util.List;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.service.IssueService;
import kr.codesquad.issuetracker.service.IssueSearchService;
import kr.codesquad.issuetracker.web.dto.issue.IssueAddRequestDto;
import kr.codesquad.issuetracker.web.dto.issue.LabelsResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueModifyRequestDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.MembersResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.MilestonesResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/labels")
	public List<LabelsResponseDto> labelList() {
		return issueSearchService.listLabels();
	}

	@GetMapping("/members")
	public List<MembersResponseDto> memberList() {
		return issueSearchService.listMembers();
	}

	@GetMapping("/milestones")
	public List<MilestonesResponseDto> milestoneList() {
		return issueSearchService.listMilestones();
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
