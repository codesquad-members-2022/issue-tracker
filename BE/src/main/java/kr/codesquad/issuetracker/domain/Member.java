package kr.codesquad.issuetracker.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "members")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "members_id")
	private Long id;

	private String name;

	private String email;

	private String githubId;

	private String imageUrl;

	@OneToMany(mappedBy = "member")
	private List<Issue> issueList = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<Comment> commentList = new ArrayList<>();
}
