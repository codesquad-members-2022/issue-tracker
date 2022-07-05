package louie.hanse.issuetracker.web.dto;

import java.util.ArrayList;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class IssueSaveRequest {
    private String title;
    private String contents;
    private List<Long> labelIds = new ArrayList<>();
    private List<Long> managerIds = new ArrayList<>();
    private Long milestoneId;
}
