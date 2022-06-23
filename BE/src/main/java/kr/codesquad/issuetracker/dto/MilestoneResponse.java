package kr.codesquad.issuetracker.dto;

import kr.codesquad.issuetracker.domain.Issue;
import kr.codesquad.issuetracker.domain.Milestone;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MilestoneResponse {

    private Long id;
    private String title;
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    private List<Issue> issueList;

    public MilestoneResponse(Milestone milestone) {
        this.id = milestone.getId();
        this.title = milestone.getTitle();
        this.content = milestone.getContent();
        this.deadline = milestone.getDeadline();
        this.issueList = milestone.getIssueList();
    }
}
