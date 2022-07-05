package louie.hanse.issuetracker.web.dto;

import lombok.Getter;
import lombok.Setter;
import louie.hanse.issuetracker.domain.Status;

@Setter
@Getter
public class IssueSearchRequest {
    private Status status;
    private Long writerId;
    private Long managerId;
    private boolean commentByMe;
    private Long labelId;
    private Long milestoneId;
}
