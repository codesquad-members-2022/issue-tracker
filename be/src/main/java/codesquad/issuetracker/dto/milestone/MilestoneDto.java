package codesquad.issuetracker.dto.milestone;

import codesquad.issuetracker.domain.Milestone;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MilestoneDto {

    private Long id;
    private String subject;
    private String description;

    private MilestoneDto() {}

    @QueryProjection
    public MilestoneDto(Long id, String subject, String description) {
        this.id = id;
        this.subject = subject;
        this.description = description;
    }

    public static MilestoneDto convertToDto(Milestone milestone) {
        if (milestone != null) {
            return new MilestoneDto(milestone.getId(), milestone.getSubject(),
                milestone.getDescription());
        }

        return null;
    }
}
