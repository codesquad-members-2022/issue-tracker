package kr.codesquad.issuetracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    @JsonIgnore
    private List<Issue> issueList;

    public MilestoneResponse(Milestone milestone) {
        this.id = milestone.getId();
        this.title = milestone.getTitle();
        this.description = milestone.getContent();
        this.deadline = milestone.getDeadline();
        this.issueList = milestone.getIssueList();
    }
}
