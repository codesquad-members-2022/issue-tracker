package codesquad.issuetracker.mapper;

import codesquad.issuetracker.domain.Label;
import codesquad.issuetracker.dto.label.LabelDto;
import java.util.List;
import java.util.stream.Collectors;

public class LabelMapper {

    public static LabelDto convertToDto(Label label) {
        if (label != null) {
            return new LabelDto(label.getId(), label.getName(), label.getDescription(),
                label.getColor(), label.isDarkText());
        }

        return null;
    }

    public static List<LabelDto> convertToListDto(List<Label> labels) {
        return labels.stream().map(LabelMapper::convertToDto).collect(Collectors.toList());
    }
}
