package dev.kukim.issues.milestone.controller.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MilestoneListResponse {

	private final Long labelsCount;
	private final Long openedMilestonesCount;
	private final Long closedMilestonesCount;
	private final List<MilestoneResponse> milestones;

	private MilestoneListResponse(Long labelsCount, Long openedMilestonesCount,
		Long closedMilestonesCount,
		List<MilestoneResponse> milestones) {
		this.labelsCount = labelsCount;
		this.openedMilestonesCount = openedMilestonesCount;
		this.closedMilestonesCount = closedMilestonesCount;
		this.milestones = milestones;
	}

	public static MilestoneListResponse of(List<MilestoneResponse> mileStoneResponse) {
		// TODO : label, milestone 카운트 조회 추가해야한다.
		return new MilestoneListResponse(null, null, null,
			mileStoneResponse);
	}
}
