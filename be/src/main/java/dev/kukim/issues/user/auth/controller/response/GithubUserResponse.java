package dev.kukim.issues.user.auth.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GithubUserResponse {

	private Long id;

	private String name;

	private String email;

	private String avatarUrl;

}
