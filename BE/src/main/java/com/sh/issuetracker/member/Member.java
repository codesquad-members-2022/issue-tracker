package com.sh.issuetracker.member;

<<<<<<< HEAD
import com.sh.issuetracker.project.Project;
import com.sh.issuetracker.user.User;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
=======
import com.sh.issuetracker.issue.Issue;
import com.sh.issuetracker.project.Project;
import com.sh.issuetracker.user.User;

import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
>>>>>>> b7681b9 ([juolly/issue-tracker #43] feat: Member)
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

// @NamedAttributeNode("issues")
@NamedEntityGraph(name = "Member.all",
	attributeNodes = {
		@NamedAttributeNode("project"),
		@NamedAttributeNode("user")})
@ToString
@Where(clause = "is_deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "issue_tracker_member")
@Entity
public class Member {
	@EmbeddedId
	private MemberId id;

	@MapsId("userId")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@MapsId("projectId")
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ColumnDefault("0")
	private boolean isDeleted = false;

/*	@ManyToMany
	@JoinTable(
		name = "issue_tracker_manager",
		joinColumns = {
			@JoinColumn(name = "project_id", referencedColumnName = "project_id"),
			@JoinColumn(name = "user_id", referencedColumnName = "user_id")
		},
		inverseJoinColumns = @JoinColumn(name = "issue_id"))
	private List<Issue> issues = new ArrayList<>();

	public List<Long> getOpenIssueIds() {
		return this.issues.stream()
			.filter(issue -> issue.openStatus())
			.map(Issue::getId)
			.collect(Collectors.toList());
	}*/
}
