package codesquad.issuetracker.dto.issue;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueDtos {

    private Long countOfOpenIssues;
    private Long countOfClosedIssues;
    private final List<IssueDto> issues;
}
