package kr.codesquad.issuetracker.web.dto.milestone;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneResponseDto {

	private int labelCount;
	private int milestoneOpenCount;
	private int milestoneClosedCount;
	private List<MilestoneDto> milestoneDtos;
}
