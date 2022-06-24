package com.sh.issuetracker.issue.search;

import com.querydsl.core.annotations.QueryProjection;
import com.sh.issuetracker.issue.IssueStatus;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class IssueSearchDto {
	private Long issueId;
	private String issueTitle;
	private IssueStatus status;
	private LocalDateTime createdAt;
	private String author;
	private String authorImage;
	private String milestoneTitle;

	@QueryProjection
	public IssueSearchDto(Long issueId, String issueTitle, IssueStatus status, LocalDateTime createdAt,
		String author, String authorImage, String milestoneName) {
		this.issueId = issueId;
		this.issueTitle = issueTitle;
		this.status = status;
		this.createdAt = createdAt;
		this.author = author;
		this.authorImage = authorImage;
		this.milestoneTitle = milestoneName;
	}
}
