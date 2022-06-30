package team29.hoorry.issuetracker.core.issue;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team29.hoorry.issuetracker.core.common.search.SearchParamParser;
import team29.hoorry.issuetracker.core.issue.domain.Issue;
import team29.hoorry.issuetracker.core.issue.domain.IssueAssignee;
import team29.hoorry.issuetracker.core.issue.domain.IssueLabel;
import team29.hoorry.issuetracker.core.issue.domain.Status;
import team29.hoorry.issuetracker.core.issue.dto.IssueFilter;
import team29.hoorry.issuetracker.core.issue.dto.request.IssueStatusUpdateRequest;
import team29.hoorry.issuetracker.core.issue.dto.request.IssuesSaveRequest;
import team29.hoorry.issuetracker.core.issue.dto.request.IssuesStatusUpdateRequest;
import team29.hoorry.issuetracker.core.issue.dto.response.CommentResponse;
import team29.hoorry.issuetracker.core.issue.dto.response.IssueDetailResponse;
import team29.hoorry.issuetracker.core.issue.dto.response.IssueLabelResponse;
import team29.hoorry.issuetracker.core.issue.dto.response.IssueMilestoneResponse;
import team29.hoorry.issuetracker.core.issue.dto.response.IssueResponse;
import team29.hoorry.issuetracker.core.issue.dto.response.IssuesResponse;
import team29.hoorry.issuetracker.core.label.LabelRepository;
import team29.hoorry.issuetracker.core.label.domain.Label;
import team29.hoorry.issuetracker.core.label.dto.LabelResponse;
import team29.hoorry.issuetracker.core.member.MemberRepository;
import team29.hoorry.issuetracker.core.member.domain.Member;
import team29.hoorry.issuetracker.core.member.dto.MemberResponse;
import team29.hoorry.issuetracker.core.milestone.MilestoneRepository;
import team29.hoorry.issuetracker.core.milestone.domain.Milestone;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IssueService {

	private static final int PAGE_SIZE = 10;

	private final IssueRepository issueRepository;
	private final MemberRepository memberRepository;
	private final LabelRepository labelRepository;
	private final MilestoneRepository milestoneRepository;

	public IssuesResponse findAll(String query, Integer page) {
		IssueFilter issueFilter = IssueFilter.from(SearchParamParser.parse(query));

		PageRequest pageable = PageRequest.of(page, PAGE_SIZE);
		Page<Issue> issues = issueRepository.findAllByIssueFilter(issueFilter, pageable);
		Page<IssueResponse> issueResponses = issues.map(IssueResponse::new);

		List<Member> members = memberRepository.findAll();
		List<Label> labels = labelRepository.findAll();
		List<Milestone> milestones = milestoneRepository.findAll();

		Long openIssueCount = issueRepository.countFilteredIssues(issueFilter, Status.OPEN);
		Long closedIssueCount = issueRepository.countFilteredIssues(issueFilter, Status.CLOSED);
		Long labelCount = (long) labels.size();
		Long milestoneCount = (long) milestones.size();

		List<IssueLabelResponse> issueLabelResponses = labels.stream()
			.map(IssueLabelResponse::from)
			.collect(Collectors.toList());

		List<IssueMilestoneResponse> issueMilestoneResponses = milestoneRepository.findAllWithRelatedIssueStatusCount();

		List<MemberResponse> memberResponses = members.stream()
			.map(MemberResponse::from)
			.collect(Collectors.toList());

		return new IssuesResponse(openIssueCount, closedIssueCount, labelCount, milestoneCount, memberResponses,
			issueLabelResponses, issueMilestoneResponses, memberResponses, issueResponses);
	}

	public IssueDetailResponse findById(Long id) {
		Issue issue = issueRepository.findByIdUsingFetchJoin(id)
			.orElseThrow(() -> new NoSuchElementException("해당 issueId를 가진 이슈는 없습니다."));

		Long issueId = issue.getId();
		String title = issue.getTitle();
		Status status = issue.getStatus();

		List<LabelResponse> labels = issue.getLabels().stream()
			.map(IssueLabel::getLabel)
			.map(LabelResponse::from)
			.collect(Collectors.toList());

		List<MemberResponse> assignees = issue.getAssignees().stream()
			.map(IssueAssignee::getAssignee)
			.map(MemberResponse::from)
			.collect(Collectors.toList());

		MemberResponse writer = MemberResponse.from(issue.getWriter());

		Milestone issueMilestone = issue.getMilestone();

		List<Issue> issueOfSameMilestone = issueRepository.findByMilestone(issueMilestone);
		Long openIssueCount = 0L;
		Long closedIssueCount = 0L;
		for (Issue aIssue : issueOfSameMilestone) {
			if (aIssue.getStatus() == Status.OPEN) {
				openIssueCount++;
			}
			if (aIssue.getStatus() == Status.CLOSED) {
				closedIssueCount++;
			}
		}

		IssueMilestoneResponse milestone = new IssueMilestoneResponse(issueMilestone.getId(), issueMilestone.getTitle(),
			openIssueCount, closedIssueCount);

		List<CommentResponse> comments = issue.getComments().stream()
			.map(comment -> CommentResponse.from(comment, List.copyOf(comment.getReactions())))
			.collect(Collectors.toList());

		return new IssueDetailResponse(issueId, title, status, labels, assignees, writer, milestone, comments);
	}

	@Transactional
	public void delete(Long id) {
		Issue issue = issueRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("해당 issueId를 가진 이슈는 없습니다."));
		issue.changeStatus(Status.DELETED);
	}

	@Transactional
	public void updateStatus(Long id, IssueStatusUpdateRequest issueStatusUpdateRequest) {
		Issue issue = issueRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("해당 issueId를 가진 이슈는 없습니다."));
		try {
			Status status = Status.valueOf(issueStatusUpdateRequest.getStatus().toUpperCase());
			issue.changeStatus(status);
		} catch (IllegalArgumentException e) {
			throw new NoSuchElementException("해당 status가 존재하지 않습니다.(status : OPEN, CLOSED)");
		}
	}

	@Transactional
	public void updateAllStatus(IssuesStatusUpdateRequest issuesStatusUpdateRequest) {
		try {
			Status status = Status.valueOf(issuesStatusUpdateRequest.getStatus().toUpperCase());
			List<Long> issueIds = issuesStatusUpdateRequest.getIssueIds();
			issueRepository.updateAllStatus(status, issueIds);
		} catch (IllegalArgumentException e) {
			throw new NoSuchElementException("해당 status가 존재하지 않습니다.(status : OPEN, CLOSED)");
		}
	}

	@Transactional
	public void save(IssuesSaveRequest issuesSaveRequest) {
		String comment = (issuesSaveRequest.getComment() == null) ? "" : issuesSaveRequest.getComment();
		Member writer = memberRepository.findById(issuesSaveRequest.getWriterId())
			.orElseThrow(() -> new NoSuchElementException("존재하지 않는 memberId입니다."));
		List<Member> assignees = memberRepository.findAllById(issuesSaveRequest.getAssigneeIds());
		List<Label> labels = labelRepository.findAllById(issuesSaveRequest.getLabelIds());
		Milestone milestone = null;
		if (issuesSaveRequest.getMilestoneId() != null) {
			milestone = milestoneRepository.findById(issuesSaveRequest.getMilestoneId())
				.orElseThrow(() -> new NoSuchElementException("존재하지 않는 milestoneId입니다."));
		}

		Issue newIssue = Issue.of(
			issuesSaveRequest.getTitle(),
			Status.OPEN,
			writer,
			new HashSet<>(),
			new HashSet<>(),
			new HashSet<>(),
			milestone
		);

		newIssue.addComment(comment, writer);
		newIssue.addAssignees(assignees);
		newIssue.addLabels(labels);

		issueRepository.save(newIssue);
	}
}
