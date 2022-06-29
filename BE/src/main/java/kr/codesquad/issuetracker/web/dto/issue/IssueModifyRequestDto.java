package kr.codesquad.issuetracker.web.dto.issue;

import java.util.List;
import kr.codesquad.issuetracker.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IssueModifyRequestDto {

	private Status status;

	private List<Long> ids;
}
