package team29.hoorry.issuetracker.core.issue.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team29.hoorry.issuetracker.core.member.domain.Member;
import team29.hoorry.issuetracker.core.member.dto.MemberResponse;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReactionResponse {

	private String emoji;
	private List<MemberResponse> members;

	public static ReactionResponse from(String emoji, List<Member> members) {
		return new ReactionResponse(emoji, members.stream().map(MemberResponse::from).collect(Collectors.toList()));
	}
}
