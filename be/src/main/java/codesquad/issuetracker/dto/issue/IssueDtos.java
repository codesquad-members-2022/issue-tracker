package codesquad.issuetracker.dto.issue;

import java.util.List;
import lombok.Getter;

@Getter
public class IssueDtos {

    private final List<IssueDto> issues;

    public IssueDtos(List<IssueDto> issues) {
        this.issues = issues;
    }
}
