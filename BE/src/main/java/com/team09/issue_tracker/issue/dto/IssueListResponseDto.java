package com.team09.issue_tracker.issue.dto;

import com.team09.issue_tracker.label.dto.LabelSelectResponseDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IssueListResponseDto {

	private Long id;
	private String title;
	private String content;
	private boolean isOpened;
	private String milestoneTitle;
	private List<LabelSelectResponseDto> labels;

}
