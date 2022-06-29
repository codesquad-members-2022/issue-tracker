package team24.issuetracker.issue.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.issue.domain.Issue;
import team24.issuetracker.issue.domain.dto.IssueListResponse;
import team24.issuetracker.issue.domain.dto.IssueRequest;
import team24.issuetracker.issue.domain.reference.IssueLabel;
import team24.issuetracker.issue.domain.reference.IssueMember;
import team24.issuetracker.issue.exception.IssueNotFoundException;
import team24.issuetracker.issue.repository.IssueRepository;
import team24.issuetracker.label.exception.LabelNotFoundException;
import team24.issuetracker.label.repository.LabelRepository;
import team24.issuetracker.member.domain.Member;
import team24.issuetracker.member.exception.MemberNotFoundException;
import team24.issuetracker.member.repository.MemberRepository;
import team24.issuetracker.milestone.domain.Milestone;
import team24.issuetracker.milestone.exception.MilestoneNotFoundException;
import team24.issuetracker.milestone.repository.MilestoneRepository;
import team24.issuetracker.uploadedfile.repository.UploadRepository;

@Service
@RequiredArgsConstructor
public class IssueService {

	private static final String MEMBER_NOT_FOUND_MESSAGE = "해당하는 ID의 멤버가 존재하지 않습니다.";
	private static final String MILESTONE_NOT_FOUND_MESSAGE = "해당하는 ID의 마일스톤이 존재하지 않습니다.";
	private static final String LABEL_NOT_FOUND_MESSAGE = "해당하는 ID의 라벨이 존재하지 않습니다.";
	private static final String ISSUE_NOT_FOUND_MESSAGE = "해당하는 ID의 이슈가 존재하지 않습니다.";
	private final IssueRepository issueRepository;
	private final LabelRepository labelRepository;
	private final MemberRepository memberRepository;
	private final MilestoneRepository milestoneRepository;
	private final UploadRepository uploadRepository;

	public List<IssueListResponse> findByWriter(@PathVariable Long id) {
		return issueRepository.findByWriter(id).stream()
			.map(IssueListResponse::new)
			.collect(Collectors.toList());
	}

	public List<IssueListResponse> findByAssignee(@PathVariable Long id) {
		return issueRepository.findByAssignee(id).stream()
			.map(IssueListResponse::new)
			.collect(Collectors.toList());
	}

	public List<IssueListResponse> findByCommenter(@PathVariable Long id) {
		return issueRepository.findByCommenter(id).stream()
			.map(IssueListResponse::new)
			.collect(Collectors.toList());
	}

	public List<IssueListResponse> findByState(Boolean isClosed) {
		return issueRepository.findByState(isClosed).stream()
			.map(IssueListResponse::new)
			.collect(Collectors.toList());
	}

	public void add(IssueRequest issueRequest) {
		Issue issue = create(issueRequest);
		List<IssueMember> assignees = issueRequest.getAssignees().stream()
			.map(memberId -> memberRepository.findById(memberId)
				.orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND_MESSAGE)))
			.map(member -> IssueMember.builder().issue(issue).member(member).build())
			.collect(Collectors.toList());

		List<IssueLabel> labels = issueRequest.getLabels().stream()
			.map(labelId -> labelRepository.findById(labelId)
				.orElseThrow(() -> new LabelNotFoundException(LABEL_NOT_FOUND_MESSAGE)))
			.map(label -> IssueLabel.builder().issue(issue).label(label).build())
			.collect(Collectors.toList());
		issue.mappingFields(assignees, labels);
		issueRepository.save(issue);
	}

	private Issue create(IssueRequest issueRequest) {
		Member writer = memberRepository.findById(issueRequest.getWriterId())
			.orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND_MESSAGE));
		if (issueRequest.getMilestone() == null) {
			Issue issue = issueRequest.toEntity(issueRequest, writer);
			issueRepository.save(issue);
			return issue;
		}
		Milestone milestone = milestoneRepository.findById(issueRequest.getMilestone())
			.orElseThrow(() -> new MilestoneNotFoundException(MILESTONE_NOT_FOUND_MESSAGE));
		Issue issue = issueRequest.toEntity(issueRequest, writer, milestone);
		issueRepository.save(issue);
		return issue;
	}

	public void updateState(Long id) {
		Issue issue = issueRepository.findById(id)
			.orElseThrow(() -> new IssueNotFoundException(ISSUE_NOT_FOUND_MESSAGE));
		issue.changeState();
		issueRepository.save(issue);
	}

	public void delete(Long id) {
		Issue issue = issueRepository.findById(id)
			.orElseThrow(() -> new IssueNotFoundException(ISSUE_NOT_FOUND_MESSAGE));
		issue.delete();
		issueRepository.save(issue);
	}
}
