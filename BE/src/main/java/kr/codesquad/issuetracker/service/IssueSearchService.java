package kr.codesquad.issuetracker.service;

import static kr.codesquad.issuetracker.exception.ErrorMessage.*;

import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.issue.repository.IssueRepository;
import kr.codesquad.issuetracker.domain.label.repository.LabelRepository;
import kr.codesquad.issuetracker.domain.member.Member;
import kr.codesquad.issuetracker.domain.member.repository.MemberRepository;
import kr.codesquad.issuetracker.domain.milestone.repository.MilestoneRepository;
import kr.codesquad.issuetracker.exception.CustomException;
import kr.codesquad.issuetracker.web.dto.comment.CommentResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.DetailRequestDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueAssigneeDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueDetailLabelDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueDetailMemberDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueDetailMilestoneDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueDetailResponse;
import kr.codesquad.issuetracker.web.dto.issue.IssueDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueLabelDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.LabelsResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.MembersResponseDto;
import kr.codesquad.issuetracker.web.dto.issue.MilestonesResponseDto;
import kr.codesquad.issuetracker.web.dto.member.MemberResponseDto;
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

	@Transactional(readOnly = true)
	public List<LabelsResponseDto> listLabels() {
		return labelRepository.findAll().stream()
			.map(LabelsResponseDto::from)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<MembersResponseDto> listMembers() {
		return memberRepository.findAll().stream()
			.map(MembersResponseDto::from)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<MilestonesResponseDto> listMilestones() {
		return milestoneRepository.findAll().stream()
			.map(MilestonesResponseDto::from)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public IssueDetailResponse detail(DetailRequestDto dto) {
		Issue issue = findIssueByIssueIdOrThrow(dto.getIssueId());
		Member member = findMemberByMemberIdOrThrow(dto.getMemberId());
		return IssueDetailResponse.of(
			issue,
			IssueDetailMemberDto.from(member),
			commentsToDto(issue, member),
			checkedAssignees(issue),
			checkedLabels(issue),
			checkMilestone(issue)
		);
	}

	private Member findMemberByMemberIdOrThrow(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
	}

	private Issue findIssueByIssueIdOrThrow(Long issueId) {
		return issueRepository.findById(issueId)
			.orElseThrow(() -> new CustomException(ISSUE_NOT_FOUND));
	}

	private IssueDetailMilestoneDto checkMilestone(Issue issue) {
		return IssueDetailMilestoneDto.from(issue.getMilestone());
	}

	private List<IssueDetailLabelDto> checkedLabels(Issue issue) {
		return issue.getLabels().stream()
			.map(IssueDetailLabelDto::from)
			.collect(Collectors.toList());
	}

	private List<IssueAssigneeDto> checkedAssignees(Issue issue) {
		return issue.getAssignees().stream()
			.map(IssueAssigneeDto::from)
			.collect(Collectors.toList());
	}

	private List<CommentResponseDto> commentsToDto(Issue issue, Member member) {
		return issue.getComments().stream()
			.map(comment -> CommentResponseDto.create(issue, member, comment))
			.collect(Collectors.toList());
	}
}
