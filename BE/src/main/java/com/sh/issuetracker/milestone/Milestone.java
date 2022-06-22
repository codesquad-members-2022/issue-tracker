package com.sh.issuetracker.milestone;

import com.sh.issuetracker.project.Project;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = false")
@Table(name = "issue_tracker_milestone")
@Entity
public class Milestone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "milestone_id")
	private Long id;

	private String milestoneTitle;
	private String description;
	private LocalDateTime completionDate;

	@ColumnDefault("0")
	private boolean isDeleted = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	@Builder
	public Milestone(String milestoneTitle, String description, Project project) {
		this.milestoneTitle = milestoneTitle;
		this.description = description;
		this.completionDate = LocalDateTime.now();
		this.isDeleted = false;
		this.project = project;
	}

	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return milestoneTitle;
	}

	public String getDescription() {
		return description;
	}

	public String getCompletionDate() {
		return completionDate.toString();
	}
}
