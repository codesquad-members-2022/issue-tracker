package codesquad.issuetracker.dto.issue;

import codesquad.issuetracker.dto.label.LabelDto;
import codesquad.issuetracker.dto.milestone.MilestoneDto;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class IssueDto {

    private Long id;
    private String subject;
    private String description;
    private String writer;
    private String profileUrl;
    private LocalDateTime createdDateTime;
    private List<LabelDto> labels;
    private MilestoneDto milestone;

    @QueryProjection
    public IssueDto(Long id, String subject, String description, String writer,
        String profileUrl, LocalDateTime createdDateTime, MilestoneDto milestone) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.writer = writer;
        this.profileUrl = profileUrl;
        this.createdDateTime = createdDateTime;
        this.milestone = milestone;
    }

    public void setLabels(List<LabelDto> labels) {
        this.labels = labels;
    }
}
