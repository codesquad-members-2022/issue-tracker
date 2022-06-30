package kr.codesquad.issuetracker.web.dto.issue;

import kr.codesquad.issuetracker.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MembersResponseDto {

	private Long memberId;

	private String memberName;

	private String profile;

	public static MembersResponseDto from(Member member) {
		return new MembersResponseDto(
			member.getId(),
			member.getName(),
			member.getImageUrl()
		);
	}
}
