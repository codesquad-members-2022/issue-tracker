package team29.hoorry.issuetracker.core.issue.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReactionSaveRequest {

	@Schema(required = true)
	private Long memberId;
	@Schema(required = true)
	private String emoji;

}
