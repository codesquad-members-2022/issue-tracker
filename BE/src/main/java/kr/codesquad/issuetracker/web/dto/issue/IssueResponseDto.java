package kr.codesquad.issuetracker.web.dto.issue;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueResponseDto {

	private int labelCount;

	private int milestoneCount;

	private int openedIssues;

	private int closedIssues;

	private List<IssueDto> issues;
}
