package dev.kukim.issues.milestone.domain;

import dev.kukim.issues.common.domain.BaseTimeEntity;
import dev.kukim.issues.issue.domain.Issue;
import dev.kukim.issues.milestone.controller.request.MilestoneCreateRequest;
import dev.kukim.issues.milestone.controller.request.MilestoneUpdateRequest;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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

	@Builder
	private Milestone(Long id, @NonNull String title, String description, LocalDate dueDate, boolean isOpen) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.isOpen = isOpen;
	}

	public static Milestone createBy(MilestoneCreateRequest request) {
		return Milestone.builder()
			.title(request.getTitle())
			.description(request.getDescription())
			.dueDate(request.getDueDate())
			.isOpen(true)
			.build();
	}

	public void update(MilestoneUpdateRequest request) {
		// TODO : 리팩토링 필요
		if (request.getTitle() != null) {
			this.title = request.getTitle();
		}
		if (request.getDescription() != null) {
			this.description = request.getDescription();
		}
		if (request.getDueDate() != null) {
			this.dueDate = request.getDueDate();
		}
		if (request.getOpen() != null) {
			this.isOpen = request.getOpen();
		}
	}
}
