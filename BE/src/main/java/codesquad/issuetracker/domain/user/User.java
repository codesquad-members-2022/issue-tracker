package codesquad.issuetracker.domain.user;

import codesquad.issuetracker.domain.Issue;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String loginId;

	private String name;

	private String password;

	private String image;

	@OneToMany(mappedBy = "user")
	private List<Issue> issues = new ArrayList<>();

	@Builder
	public User(String loginId, String name, String password, String image) {
		this.loginId = loginId;
		this.name = name;
		this.password = password;
		this.image = image;
	}

}
