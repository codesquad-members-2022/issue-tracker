package kr.codesquad.issuetracker.service;

import static kr.codesquad.issuetracker.exception.ErrorMessage.ISSUE_NOT_FOUND;

import java.util.List;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.issue.repository.IssueRepository;
import kr.codesquad.issuetracker.exception.CustomException;
import kr.codesquad.issuetracker.web.dto.issue.IssueAddRequestDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueModifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class IssueService {

	private final IssueRepository issueRepository;
	private final MemberService memberService;
	private final LabelService labelService;
	private final MilestonesService milestonesService;

	@Transactional
	public void statusModify(IssueModifyRequestDto dto) {
		List<Long> ids = dto.getIds();
		for (Long id : ids) {
			Issue issue = findIssueByIdOrThrow(id);

			issue.updateStatus(dto.getStatus());
		}
	}

	private Issue findIssueByIdOrThrow(Long id) {
		return issueRepository.findById(id)
			.orElseThrow(() -> new CustomException(ISSUE_NOT_FOUND));
	}

	public void add(IssueAddRequestDto dto) {
		Issue issue = Issue.createIssue(
			dto.getTitle(),
			dto.getDescription(),
			memberService.findUserByIdOrThrow(dto.getMemberId()),
			memberService.findAssignees(dto.getAssignees()),
			labelService.findLabels(dto.getLabels()),
			milestonesService.findMilestoneById(dto.getMilestoneId())
		);
		issueRepository.save(issue);
	}
}
