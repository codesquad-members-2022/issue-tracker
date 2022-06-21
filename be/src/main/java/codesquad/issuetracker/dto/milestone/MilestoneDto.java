package codesquad.issuetracker.dto.milestone;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MilestoneDto {

    private Long id;
    private String subject;
    private String description;

    @QueryProjection
    public MilestoneDto(Long id, String subject, String description) {
        this.id = id;
        this.subject = subject;
        this.description = description;
    }
}
