package kr.codesquad.issuetracker.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.issue.repository.IssueRepository;
import kr.codesquad.issuetracker.domain.label.repository.LabelRepository;
import kr.codesquad.issuetracker.domain.milestone.repository.MilestoneRepository;
import kr.codesquad.issuetracker.web.dto.issue.IssueDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class IssueService {

	private final IssueRepository issueRepository;

	private final LabelRepository labelRepository;

	private final MilestoneRepository milestoneRepository;

	@Transactional(readOnly = true)
	public IssueResponseDto list(Status status) {
		return getIssueResponseDto(status);
	}

	private IssueResponseDto getIssueResponseDto(Status status) {
		return new IssueResponseDto(
			(int) labelRepository.count(),
			(int) milestoneRepository.count(),
			countOpened(),
			countClosed(),
			findIssueDtos(status)
		);
	}

	private List<IssueDto> findIssueDtos(Status status) {
		return issueRepository.findAllByStatus(status).stream()
			.map(IssueDto::from)
			.collect(Collectors.toList());
	}

	private int countClosed() {
		return (int) issueRepository.countByStatus(Status.CLOSED);
	}

	private int countOpened() {
		return (int) issueRepository.countByStatus(Status.OPEN);
	}

}
