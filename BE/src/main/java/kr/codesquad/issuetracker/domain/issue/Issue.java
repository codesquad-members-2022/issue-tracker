package kr.codesquad.issuetracker.domain.issue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import kr.codesquad.issuetracker.domain.BaseTimeEntity;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.comment.Comment;
import kr.codesquad.issuetracker.domain.image.Image;
import kr.codesquad.issuetracker.domain.label.Label;
import kr.codesquad.issuetracker.domain.member.Member;
import kr.codesquad.issuetracker.domain.milestone.Milestone;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "issue")
public class Issue extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_id")
	private Long id;

	private String title;

	@Enumerated(EnumType.STRING)
	private Status status;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member writer;

	@OneToMany(mappedBy = "issue")
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "issue")
	private List<Image> images = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "milestone_id")
	private Milestone milestone;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "assignee",
		joinColumns = @JoinColumn(name = "issue_id"),
		inverseJoinColumns = @JoinColumn(name = "member_id")
	)
	private List<Member> assignees = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
		name = "issue_label",
		joinColumns = @JoinColumn(name = "issue_id"),
		inverseJoinColumns = @JoinColumn(name = "label_id")
	)
	private List<Label> labels = new ArrayList<>();

	public static Issue createIssue(String title, String description, Member writer, List<Member> assignees,
		List<Label> labels, Milestone milestone) {
		return Issue.builder()
			.title(title)
			.content(description)
			.writer(writer)
			.assignees(assignees)
			.labels(labels)
			.status(Status.OPEN)
			.milestone(milestone)
			.build();
	}

	@Builder
	public Issue(String title, Status status, String content, Member writer, Milestone milestone,
		List<Member> assignees,
		List<Label> labels) {
		this.title = title;
		this.status = status;
		this.content = content;
		this.writer = writer;
		this.milestone = milestone;
		this.assignees = assignees;
		this.labels = labels;
	}

	public boolean isOpened() {
		return this.status.equals(Status.OPEN);
	}

	public boolean isClosed() {
		return this.status.equals(Status.CLOSED);
	}

	public Issue deleteMilestone() {
		this.milestone = null;
		return this;
	}

	public Issue deleteLabel(Label label) {
		labels = labels.stream()
			.filter(l -> !l.equals(label))
			.collect(Collectors.toList());
		return this;
	}

	public void updateStatus(Status status) {
		this.status = status;
	}
}
