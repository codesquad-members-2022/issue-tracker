package com.team09.issue_tracker.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueDetailResponseDto {

	private Long id;
	private String title;
	private String content;
	private boolean isOpened;
	private String milestoneTitle;
	private boolean isEditable;

}
