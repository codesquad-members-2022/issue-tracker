package kr.codesquad.issuetracker.web.dto.label;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LabelResponseDto {

	private int labelCount;
	private int milestoneCount;
	private List<LabelDto> labels;
}
