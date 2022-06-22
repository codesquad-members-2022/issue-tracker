package team24.issuetracker.web.dto.issuelist;

import lombok.Getter;
import team24.issuetracker.domain.User;

@Getter
public class IssueListUserResponse {

	private final Long id;
	private final String name;
	private final String email;
	private final String profileImage;

	public IssueListUserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.profileImage = user.getProfileImage();
	}
}
