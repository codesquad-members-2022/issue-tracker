package louie.hanse.issuetracker.web.dto.label;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import louie.hanse.issuetracker.domain.Label;

@Getter
public class LabelListResponse {
    private int count;
    private List<LabelDetailResponse> labels;

    public LabelListResponse(List<Label> labels) {
        this.count = labels.size();
        this.labels = labels.stream()
            .map(LabelDetailResponse::new)
            .collect(Collectors.toList());
    }
}
