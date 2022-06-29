package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.issue.domain.Issue;
import com.team09.issue_tracker.issue.domain.IssueAssignee;
import com.team09.issue_tracker.member.Member;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IssueAssigneeService {

	private final IssueAssigneeRepository issueAssigneeRepository;

	public void savedIssueAssignee(Issue issue, List<Long> assigneeIds) {
		if (assigneeIds.size() > 0) {
			List<IssueAssignee> issueAssignees = assigneeIds.stream()
				.map(assigneeId -> IssueAssignee.of(issue, Member.of(assigneeId)))
				.collect(Collectors.toList());

			Set<IssueAssignee> assigneeSet = Set.copyOf(
				issueAssigneeRepository.saveAll(issueAssignees));

			//연관관계 편의메서드
			issue.addIssueAssignee(assigneeSet);
		}
	}
}
