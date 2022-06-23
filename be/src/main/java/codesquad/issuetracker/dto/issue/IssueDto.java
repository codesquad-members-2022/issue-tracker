package codesquad.issuetracker.dto.issue;

import codesquad.issuetracker.domain.Issue;
import codesquad.issuetracker.domain.IssueStatus;
import codesquad.issuetracker.domain.Member;
import codesquad.issuetracker.dto.label.LabelDto;
import codesquad.issuetracker.dto.label.LabelDtos;
import codesquad.issuetracker.dto.member.MemberDto;
import codesquad.issuetracker.dto.member.MemberDtos;
import codesquad.issuetracker.dto.milestone.MilestoneDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IssueDto {

    private Long id;
    private IssueStatus status;
    private String subject;
    private String description;
    private String writer;
    private String profileUrl;
    private LocalDateTime createdDateTime;
    private MilestoneDto milestone;
    private List<MemberDto> assignees;
    private List<LabelDto> labels;

    private IssueDto() {}

    public static IssueDto of(Issue issue) {
        Member writer = issue.getWriter();
        return new IssueDto(
            issue.getId(), issue.getStatus(), issue.getSubject(), issue.getDescription(),
            writer.getIdentity(), writer.getProfileUrl(), issue.getCreatedDateTime(),
            MilestoneDto.convertToDto(issue.getMilestone()),
            MemberDtos.convertToListDto(issue.assignees()),
            LabelDtos.convertToListDto(issue.labels())
        );
    }
}
