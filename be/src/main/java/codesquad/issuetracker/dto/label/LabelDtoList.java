package codesquad.issuetracker.dto.label;

import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
import lombok.Getter;

@Getter
public class LabelDtoList {

    private final List<LabelDto> labels;

    @QueryProjection
    public LabelDtoList(List<LabelDto> labels) {
        this.labels = labels;
    }
}
