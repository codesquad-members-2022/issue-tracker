package codesquad.issuetracker.dto.label;

import java.util.List;
import lombok.Getter;

@Getter
public class LabelDtoList {

    private final List<LabelDto> labels;

    public LabelDtoList(List<LabelDto> labels) {
        this.labels = labels;
    }
}
