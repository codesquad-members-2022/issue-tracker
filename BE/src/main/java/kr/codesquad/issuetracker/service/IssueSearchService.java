package kr.codesquad.issuetracker.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.issue.repository.IssueRepository;
import kr.codesquad.issuetracker.domain.label.Label;
import kr.codesquad.issuetracker.domain.label.repository.LabelRepository;
import kr.codesquad.issuetracker.domain.member.repository.MemberRepository;
import kr.codesquad.issuetracker.domain.milestone.repository.MilestoneRepository;
import kr.codesquad.issuetracker.web.dto.issue.IssueDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.LabelsResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.MembersResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.MilestonesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class IssueSearchService {

	private final IssueRepository issueRepository;

	private final LabelRepository labelRepository;

	private final MilestoneRepository milestoneRepository;

	private final MemberRepository memberRepository;

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

	public List<LabelsResponseDto> listLabels() {
		return labelRepository.findAll().stream()
			.map(LabelsResponseDto::from)
			.collect(Collectors.toList());
	}

	public List<MembersResponseDto> listMembers() {
		return memberRepository.findAll().stream()
			.map(MembersResponseDto::from)
			.collect(Collectors.toList());
	}

	public List<MilestonesResponseDto> listMilestones() {
		return milestoneRepository.findAll().stream()
			.map(MilestonesResponseDto::from)
			.collect(Collectors.toList());
	}
}
