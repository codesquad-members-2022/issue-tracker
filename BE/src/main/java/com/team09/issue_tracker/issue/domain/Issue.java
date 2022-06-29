package com.team09.issue_tracker.issue.domain;

import com.team09.issue_tracker.comment.Comment;
import com.team09.issue_tracker.common.BaseTimeEntity;
import com.team09.issue_tracker.common.CommonResponseDto;
import com.team09.issue_tracker.issue.dto.IssueDetailResponseDto;
import com.team09.issue_tracker.issue.dto.IssueListResponseDto;
import com.team09.issue_tracker.label.Label;
import com.team09.issue_tracker.milestone.Milestone;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "issue", fetch = FetchType.LAZY)
	private Set<IssueLabel> issueLabels = new HashSet<>();


	@OneToMany(mappedBy = "issue", fetch = FetchType.LAZY)
	private Set<IssueAssignee> issueAssignees = new HashSet<>();

	public boolean isWriter(Long memberId) {
		return memberId.equals(this.memberId);
	}

	public void addIssueLabel(Set<IssueLabel> issueLabels) {
		this.issueLabels = issueLabels;
	}

	public void addIssueAssignee(Set<IssueAssignee> issueAssignees) {
		this.issueAssignees = issueAssignees;
	}

	public static Issue of(Long issueId) {
		return Issue.builder()
			.id(issueId)
			.build();
	}

	public static Issue of(String title, String content, Long memberId, boolean isOpened,
		Milestone milestone) {
		return Issue.builder()
			.title(title)
			.content(content)
			.milestone(milestone)
			.isOpened(isOpened)
			.memberId(memberId)
			.build();
	}

	public CommonResponseDto toCommonResponse() {
		return new CommonResponseDto(id);
	}

	public IssueListResponseDto toListResponse() {
		return IssueListResponseDto.builder()
			.id(id)
			.title(title)
			.content(content)
			.isOpened(isOpened)
			.milestoneTitle(Optional.ofNullable(milestone)
				.map(Milestone::getTitle)
				.orElse(""))
			.labels(issueLabels.stream()
				.map(IssueLabel::getLabel)
				.map(Label::toResponseDto)
				.collect(Collectors.toList()))
			.build();
	}

	public IssueDetailResponseDto toDetailResponse(boolean isEditable) {
		return IssueDetailResponseDto.builder()
			.id(id)
			.title(title)
			.content(content)
			.isOpened(isOpened)
			.milestoneTitle(Optional.ofNullable(milestone)
				.map(Milestone::getTitle)
				.orElse(""))
			.isEditable(isEditable)
			.build();
	}

	public void update(String title, String content, Milestone milestone, boolean opened) {
		this.title = title;
		this.content = content;
		this.milestone = milestone;
		isOpened = opened;
	}
}
