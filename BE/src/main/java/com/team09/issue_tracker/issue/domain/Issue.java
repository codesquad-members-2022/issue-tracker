package com.team09.issue_tracker.issue.domain;

import com.team09.issue_tracker.common.BaseTimeEntity;
import com.team09.issue_tracker.common.CommonResponseDto;
import com.team09.issue_tracker.issue.dto.IssueDetailResponseDto;
import com.team09.issue_tracker.issue.dto.IssueSaveRequestDto;
import com.team09.issue_tracker.issue.dto.IssueListResponseDto;
import com.team09.issue_tracker.label.Label;
import com.team09.issue_tracker.milestone.Milestone;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import lombok.NoArgsConstructor;

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
	private List<IssueLabel> issueLabels = new ArrayList<>();

	public boolean isWriter(Long memberId) {
		if (memberId.equals(this.memberId)) {
			return true;
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public static Issue fromForMandatory(IssueSaveRequestDto issueSaveRequestDto,
		boolean isOpened, Long memberId) {
		return Issue.builder()
			.title(issueSaveRequestDto.getTitle())
			.content(issueSaveRequestDto.getContent())
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

}
