package team24.issuetracker.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class GitHubUser {

	@JsonProperty(value = "id")
	private String serialNumber;

	@JsonProperty(value = "login")
	private String name;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "avatar_url")
	private String profileImage;
}
