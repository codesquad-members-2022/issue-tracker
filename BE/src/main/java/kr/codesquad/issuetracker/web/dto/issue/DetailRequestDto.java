package kr.codesquad.issuetracker.web.dto.issue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailRequestDto {

	private Long issueId;

	private Long memberId;
}
