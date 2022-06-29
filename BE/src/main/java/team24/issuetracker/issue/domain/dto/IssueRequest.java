package team24.issuetracker.issue.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import team24.issuetracker.issue.domain.Issue;
import team24.issuetracker.member.domain.Member;
import team24.issuetracker.milestone.domain.Milestone;

@Getter
@RequiredArgsConstructor
public class IssueRequest {

	private final String title;
	private final String content;
	private final Long writerId;
	private final List<Long> assignees;
	private final List<Long> labels;
	private final Long milestone;


	public Issue toEntity(IssueRequest issueRequest, Member writer) {
		return Issue.builder()
			.title(issueRequest.getTitle())
			.content(issueRequest.getContent())
			.writtenTime(LocalDateTime.now())
			.writer(writer)
			.build();
	}

	public Issue toEntity(IssueRequest issueRequest, Member writer, Milestone milestone) {
		return Issue.builder()
			.title(issueRequest.getTitle())
			.content(issueRequest.getContent())
			.writtenTime(LocalDateTime.now())
			.writer(writer)
			.milestone(milestone)
			.build();
	}
}
