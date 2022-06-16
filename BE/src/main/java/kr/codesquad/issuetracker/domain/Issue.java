package kr.codesquad.issuetracker.domain;

import static javax.persistence.FetchType.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issues_id")
	private Long id;

	private String title;

	private boolean status;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime createdTime;

	private String content;

	@OneToMany(mappedBy = "issue")
	private List<IssueMembers> issueMembersList = new ArrayList<>();

	@ManyToOne(fetch = LAZY)
	private Member member;

	@OneToMany(mappedBy = "issue")
	private List<Comment> commentList = new ArrayList<>();

	@ManyToOne(fetch = LAZY)
	private Milestone milestone;

	@OneToMany(mappedBy = "issue")
	private List<IssueLabels> issueLabelsList = new ArrayList<>();
}
