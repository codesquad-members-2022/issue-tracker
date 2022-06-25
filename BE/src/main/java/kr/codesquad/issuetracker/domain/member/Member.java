package kr.codesquad.issuetracker.domain.member;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import kr.codesquad.issuetracker.auth.dto.AccessTokenResponseDto;
import kr.codesquad.issuetracker.auth.dto.UserProfile;
import kr.codesquad.issuetracker.domain.comment.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "members")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	private String name;

	private String email;

	private String imageUrl;

	private String token;

	@OneToMany(mappedBy = "writer")
	private List<Comment> comments = new ArrayList<>();

	public Member(String name, String email, String imageUrl, String token) {
		this.name = name;
		this.email = email;
		this.imageUrl = imageUrl;
		this.token = token;
	}

	public static Member createMember(UserProfile userInfo, String token) {
		return new Member(
			userInfo.getLogin(),
			userInfo.getEmail(),
			userInfo.getAvatarUrl(),
			token
		);
	}

	public Member update(UserProfile userInfo, String token) {
		return new Member(
			userInfo.getLogin(),
			userInfo.getEmail(),
			userInfo.getAvatarUrl(),
			token
		);
	}
}
