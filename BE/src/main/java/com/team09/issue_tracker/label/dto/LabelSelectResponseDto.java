package com.team09.issue_tracker.label.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LabelSelectResponseDto {

	private Long id;
	private String title;
	private String description;
	private String backgroundColor;
	private boolean isDarkMode;

}
