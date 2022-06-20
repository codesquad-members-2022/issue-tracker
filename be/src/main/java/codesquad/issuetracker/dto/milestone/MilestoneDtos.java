package codesquad.issuetracker.dto.milestone;

import java.util.List;
import lombok.Getter;

@Getter
public class MilestoneDtos {

    private final List<MilestoneDto> milestones;

    public MilestoneDtos(List<MilestoneDto> milestones) {
        this.milestones = milestones;
    }
}
