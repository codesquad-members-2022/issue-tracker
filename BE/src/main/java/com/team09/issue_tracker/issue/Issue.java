package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.common.BaseTimeEntity;
import com.team09.issue_tracker.issue.dto.IssueListResponseDto;
import com.team09.issue_tracker.milestone.Milestone;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Issue extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_id")
	private Long id;

	private String title;

	private String content;

	private boolean isOpened;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "milestone_id")
	private Milestone milestone;

	private Long memberId;

	@OneToMany(mappedBy = "issue", fetch = FetchType.LAZY)
	private List<IssueLabel> issueLabels = new ArrayList<>();

	public IssueListResponseDto toResponseDto() {
		return IssueListResponseDto.builder()
			.id(id)
			.title(title)
			.content(content)
			.milestoneTitle(milestone.getTitle())
			.labels(
				issueLabels.stream().map(issueLabel -> issueLabel.getLabel().toResponseDto())
					.collect(Collectors.toList()))
			.build();
	}
}
