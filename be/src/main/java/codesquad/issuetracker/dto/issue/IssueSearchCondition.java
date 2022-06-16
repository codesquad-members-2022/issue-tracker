package codesquad.issuetracker.dto.issue;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IssueSearchCondition {

    private String status;
    private Long writerId;
    private Long assigneeId;
    private Long replierId;
    private Long labelId;
    private Long milestoneId;
}
