package team29.hoorry.issuetracker.core.issue.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team29.hoorry.issuetracker.core.common.BaseTimeEntity;
import team29.hoorry.issuetracker.core.label.domain.Label;
import team29.hoorry.issuetracker.core.member.domain.Member;
import team29.hoorry.issuetracker.core.milestone.domain.Milestone;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Issue extends BaseTimeEntity {

	@Id
	@Column(name = "issue_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer_id", nullable = false)
	private Member writer;

	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments = new HashSet<>();

	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<IssueAssignee> assignees = new HashSet<>();

	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<IssueLabel> labels = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "milestone_id")
	private Milestone milestone;

	public static Issue of (String title, Status status, Member writer,
		Set<Comment> comments, Set<IssueAssignee> assignees,
		Set<IssueLabel> labels, Milestone milestone) {
		return new Issue(null, title, status, writer, comments, assignees
		, labels, milestone);
	}

	public void changeStatus(Status status) {
		this.status = status;
	}

	public void addComment(String content, Member writer) {
		this.comments.add(Comment.of(this, writer, content, new HashSet<>())) ;
	}

	public void addLabels(List<Label> labels) {
		for (Label label : labels) {
			this.labels.add(IssueLabel.of(this, label));
		}
	}

	public void addAssignees(List<Member> assignees) {
		for (Member assignee : assignees) {
			this.assignees.add(IssueAssignee.of(this, assignee));
		}
	}
}
