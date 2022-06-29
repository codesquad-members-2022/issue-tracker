package team24.issuetracker.member.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {
	private Long memberId;
	private String name;
	private String profileImage;
}
