package codesquad.issuetracker.dto.milestone;

import java.util.List;
import lombok.Getter;

@Getter
public class MilestoneDtos {

    private List<MilestoneDto> milestones;

    private MilestoneDtos() {}

    public MilestoneDtos(List<MilestoneDto> milestones) {
        this.milestones = milestones;
    }
}
