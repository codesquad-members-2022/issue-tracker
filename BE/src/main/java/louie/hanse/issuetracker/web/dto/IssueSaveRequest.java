package louie.hanse.issuetracker.web.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class IssueSaveRequest {
    private String title;
    private String contents;
    private List<Long> labelIds;
    private List<Long> managerIds;
    private Long milestoneId;
}
