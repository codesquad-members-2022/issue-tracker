package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.issue.domain.Issue;
import com.team09.issue_tracker.issue.domain.IssueAssignee;
import com.team09.issue_tracker.issue.domain.IssueLabel;
import com.team09.issue_tracker.member.Member;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IssueLabelService {

	private final IssueLabelRepository issueLabelRepository;

	void saveIssueLabel(Issue issue, List<Long> labelIds) {
		if (!labelIds.isEmpty()) {
			List<IssueLabel> issueLabels = labelIds.stream()
				.map(labelId -> IssueLabel.of(issue.getId(), labelId))
				.collect(Collectors.toList());

			Set<IssueLabel> savedIssueLabel = Set.copyOf(issueLabelRepository.saveAll(issueLabels));

			//연관관계 편의 메서드
			issue.addIssueLabel(savedIssueLabel);
		}
	}
}
