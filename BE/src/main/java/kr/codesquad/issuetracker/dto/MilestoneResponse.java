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
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    private int openedIssues;
    private int closedIssues;

    public MilestoneResponse(Milestone milestone) {
        this.id = milestone.getId();
        this.title = milestone.getTitle();
        this.description = milestone.getContent();
        this.deadline = milestone.getDeadline();
        List<Issue> issueList = milestone.getIssueList();
        countIssues(issueList);
        this.openedIssues = getOpenedIssues();
        this.closedIssues = getClosedIssues();
    }


    private void countIssues(List<Issue> issueList){
        for (Issue issue : issueList) {
            if (issue.isStatus()) {
                openedIssues ++;
            }
            if (!issue.isStatus()){
                closedIssues ++;
            }
        }
    }
}
