package kr.codesquad.issuetracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.codesquad.issuetracker.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class IssueResponse {

    private Long id;
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdTime;
    private String content;
    private String milestoneName;

    @JsonProperty("labels")
    private List<LabelResponse> issueLabelsList;

    public IssueResponse(Issue issue) {
        this.id = issue.getId();
        this.title = issue.getTitle();
        this.createdTime = issue.getCreatedTime();
        this.content = issue.getContent();
        this.milestoneName = issue.getMilestone().getTitle();
        List<IssueLabels> issueLabels = issue.getIssueLabelsList();
        this.issueLabelsList = issueLabels.stream()
                .map(IssueLabels::getLabel)
                .map(label -> new LabelResponse(label.getId(), label.getTitle(),
                        label.getBackgroundColor(), label.getTextColor(), label.getContent()))
                .collect(Collectors.toList());
    }
}
