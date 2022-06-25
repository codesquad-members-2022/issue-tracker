package com.team09.issue_tracker.issue.dto;

import com.team09.issue_tracker.issue.domain.IssueLabel;
import com.team09.issue_tracker.member.Member;
import com.team09.issue_tracker.milestone.Milestone;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueSaveServiceDto {

	private String title;
	private String content;
	private Milestone milestone;
	private List<IssueLabel> issueLabels;
	private boolean isOpened;
	private Long memberId;

}
