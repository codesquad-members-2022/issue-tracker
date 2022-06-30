package team29.hoorry.issuetracker.core.issue.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Schema(description = "이슈 등록 요청")
public class IssuesSaveRequest {

	@Schema(required = true)
	private String title;

	private String comment;

	@Schema(required = true)
	private Long writerId;

	private List<Long> assigneeIds;

	private List<Long> labelIds;

	private Long milestoneId;

}
