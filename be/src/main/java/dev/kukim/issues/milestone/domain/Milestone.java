package dev.kukim.issues.milestone.domain;

import dev.kukim.issues.common.domain.BaseTimeEntity;
import dev.kukim.issues.issue.domain.Issue;
import dev.kukim.issues.milestone.controller.request.MilestoneCreateRequest;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
public class Milestone extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private LocalDate dueDate;
	private boolean isOpen;

	@OneToMany(mappedBy = "milestone")
	private List<Issue> issues;

	private Milestone(String title, String description, LocalDate dueDate, boolean isOpen) {
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.isOpen = isOpen;
	}

	public static Milestone of(String title, String description, LocalDate dueDate, boolean isOpen) {
		return new Milestone(title, description, dueDate, isOpen);
	}

	public static Milestone of(MilestoneCreateRequest milestoneCreateRequest) {
		return new Milestone(milestoneCreateRequest.getTitle(), milestoneCreateRequest.getDescription(),
			milestoneCreateRequest.getDueDate(), true);
	}
}
