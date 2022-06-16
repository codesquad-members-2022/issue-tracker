package codesquad.issuetracker.dto.issue;

import java.util.List;
import lombok.Getter;

@Getter
public class IssueDtoList {

    private final List<IssueDto> issues;

    public IssueDtoList(List<IssueDto> issues) {
        this.issues = issues;
    }
}
