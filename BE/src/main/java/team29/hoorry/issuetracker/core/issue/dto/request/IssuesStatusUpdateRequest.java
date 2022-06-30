package team29.hoorry.issuetracker.core.issue.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IssuesStatusUpdateRequest {

	private String status;
	private List<Long> issueIds;

}
