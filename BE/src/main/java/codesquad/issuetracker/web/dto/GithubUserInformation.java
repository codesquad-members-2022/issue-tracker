package codesquad.issuetracker.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class GithubUserInformation {

	@JsonProperty("login")
	private String userId;

	@JsonProperty("name")
	private String username;

	@JsonProperty("node_id")
	private String password;

	@JsonProperty("avatar_url")
	private String profileImage;

	public GithubUserInformation(String userId, String username, String password,
		String profileImage) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.profileImage = profileImage;
	}

}
