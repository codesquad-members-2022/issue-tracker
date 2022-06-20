package codesquad.issuetracker.dto.issue;

import codesquad.issuetracker.domain.Issue;
import codesquad.issuetracker.domain.Label;
import codesquad.issuetracker.domain.Member;
import codesquad.issuetracker.domain.Milestone;
import codesquad.issuetracker.dto.label.LabelDto;
import codesquad.issuetracker.dto.milestone.MilestoneDto;
import codesquad.issuetracker.mapper.LabelMapper;
import codesquad.issuetracker.mapper.MilestoneMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueDto {

    private Long id;
    private String subject;
    private String description;
    private String writer;
    private String profileUrl;
    private LocalDateTime createdDateTime;
    private MilestoneDto milestone;
    private List<LabelDto> labels;

    private IssueDto() {}

    public static IssueDto of(Issue issue, Milestone milestone, List<Label> labels) {
        Member writer = issue.getWriter();
        return new IssueDto(issue.getId(), issue.getSubject(), issue.getDescription(),
            writer.getIdentity(), writer.getProfileUrl(), issue.getCreatedDateTime(),
            MilestoneMapper.convertToDto(milestone),
            LabelMapper.convertToListDto(labels));
    }
}
