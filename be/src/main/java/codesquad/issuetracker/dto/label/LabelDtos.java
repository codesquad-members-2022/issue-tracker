package codesquad.issuetracker.dto.label;

import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
import lombok.Getter;

@Getter
public class LabelDtos {

    private final List<LabelDto> labels;

    @QueryProjection
    public LabelDtos(List<LabelDto> labels) {
        this.labels = labels;
    }
}
