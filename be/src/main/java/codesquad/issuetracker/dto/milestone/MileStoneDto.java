package codesquad.issuetracker.dto.milestone;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MileStoneDto {

    private Long id;
    private String subject;

    @QueryProjection
    public MileStoneDto(Long id, String subject) {
        this.id = id;
        this.subject = subject;
    }
}
