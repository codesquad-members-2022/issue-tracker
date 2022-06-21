package team24.issuetracker.web.dto;

import lombok.Getter;
import team24.issuetracker.domain.User;

@Getter
public class IssueListUserResponse {

	private Long id;
	private String name;
	private String email;
	private String profileImage;

	public IssueListUserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.profileImage = user.getProfileImage();
	}
}
