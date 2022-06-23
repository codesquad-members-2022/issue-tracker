package codesquad.issuetracker.dto.label;

import codesquad.issuetracker.domain.Label;
import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class LabelDtos {

    private List<LabelDto> labels;

    private LabelDtos() {}

    @QueryProjection
    public LabelDtos(List<LabelDto> labels) {
        this.labels = labels;
    }

    public static List<LabelDto> convertToListDto(List<Label> labels) {
        return labels.stream().map(LabelDto::convertToDto).collect(Collectors.toList());
    }
}
