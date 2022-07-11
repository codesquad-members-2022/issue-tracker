package louie.hanse.issuetracker.web.dto.milestone;

import lombok.Getter;
import louie.hanse.issuetracker.domain.Milestone;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MilestoneListResponse {
    private int openedMileStoneCount;
    private int closedMileStoneCount;
    private List<MilestoneDetailResponse> milestones;

    public MilestoneListResponse(int openedMileStoneCount, int closedMileStoneCount, List<Milestone> milestones) {
        this.openedMileStoneCount = openedMileStoneCount;
        this.closedMileStoneCount = closedMileStoneCount;
        this.milestones = milestones.stream()
                .map(MilestoneDetailResponse::new)
                .collect(Collectors.toList());
    }
}
