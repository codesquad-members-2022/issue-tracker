package com.sh.issuetracker.issue.search;

import com.querydsl.core.annotations.QueryProjection;
import com.sh.issuetracker.label.TextColor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class LabelSearchDto {
	private String name;
	private String backgroundColor;
	private TextColor fontColor;

	@QueryProjection
	public LabelSearchDto(String name, String backgroundColor, TextColor fontColor) {
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.fontColor = fontColor;
	}
}
