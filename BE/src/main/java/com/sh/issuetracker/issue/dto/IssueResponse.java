package com.sh.issuetracker.issue.dto;

import com.sh.issuetracker.issue.Issue;
import com.sh.issuetracker.label.Label;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class IssueResponse {
	@Getter
	@Builder(access = AccessLevel.PRIVATE)
	@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
	public static class Row {
		private final Long issueId;
		private final String issueTitle;
		private final String issueStatus;
		private final int issueNumber;
		private final String author;
		private final String authorImage;
		private final String createdAt;
		private final String milestoneTitle;
		private final List<LabelDesign> labels;

		public static Row from(Issue issue) {
			return Row.builder()
				.issueId(issue.getId())
				.issueTitle(issue.getTitle())
				.issueStatus(issue.getStatus())
				.issueNumber(issue.getOrder())
				.author(issue.author())
				.authorImage(issue.authorImage())
				.createdAt(issue.getCreatedAt())
				.milestoneTitle(issue.milestoneTitle())
				.labels(toLabelDesigns(issue))
				.build();
		}

		private static List<LabelDesign> toLabelDesigns(Issue issue) {
			return issue.labels().stream()
				.map(LabelDesign::new)
				.collect(Collectors.toList());
		}
	}

	public static class LabelDesign {
		private final String name;
		private final String backgroundColor;
		private final String fontColor;

		public LabelDesign(Label label) {
			this.name = label.getName();
			this.backgroundColor = label.getBackgroundColor();
			this.fontColor = label.getFontColorText();
		}
	}
}
