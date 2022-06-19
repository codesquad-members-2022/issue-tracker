package codesquad.issuetracker.dto.milestone;

import java.util.List;
import lombok.Getter;

@Getter
public class MilestoneDtoList {

    private final List<MilestoneDto> milestones;

    public MilestoneDtoList(List<MilestoneDto> milestones) {
        this.milestones = milestones;
    }
}
