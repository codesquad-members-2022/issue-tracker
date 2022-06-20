package codesquad.issuetracker.dto.issue;

import codesquad.issuetracker.domain.IssueStatus;
import java.util.List;
import lombok.Getter;

@Getter
public class IssueStatusUpdateForm {

    private IssueStatus updatedStatus;
    private List<Long> idOfIssues;

}
