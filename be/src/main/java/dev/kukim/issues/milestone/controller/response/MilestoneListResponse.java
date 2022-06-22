package dev.kukim.issues.milestone.controller.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MilestoneListResponse {

	private final Long labelsCount;
	private final Long openedMilestonesCount;
	private final Long closedMilestonesCount;
	private final List<MileStoneResponse> milestones;

	private MilestoneListResponse(Long labelsCount, Long openedMilestonesCount,
		Long closedMilestonesCount,
		List<MileStoneResponse> milestones) {
		this.labelsCount = labelsCount;
		this.openedMilestonesCount = openedMilestonesCount;
		this.closedMilestonesCount = closedMilestonesCount;
		this.milestones = milestones;
	}

	public static MilestoneListResponse of(List<MileStoneResponse> mileStoneResponse) {
		// TODO : label, milestone 카운트 조회 추가해야한다.
		return new MilestoneListResponse(null, null, null,
			mileStoneResponse);
	}
}
