package kr.codesquad.issuetracker.domain.milestone;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import kr.codesquad.issuetracker.domain.BaseTimeEntity;
import kr.codesquad.issuetracker.domain.Status;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.web.dto.milestone.MilestoneEditRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "milestone")
public class Milestone extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "milestone_id")
	private Long id;

	private String title;

	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate deadLine;

	@OneToMany(mappedBy = "milestone")
	private List<Issue> issues = new ArrayList<>();

	public Milestone(String title, String description, LocalDate deadLine) {
		this.title = title;
		this.description = description;
		this.deadLine = deadLine;
		this.status = Status.OPEN;
	}


	public int countOpenIssue() {
		return (int) issues.stream()
			.filter(issue -> issue.isOpenOrClosed(Status.OPEN.getValue()))
			.count();
	}

	public int countClosedIssue() {
		return (int) issues.stream()
			.filter(issue -> issue.isOpenOrClosed(Status.CLOSED.getValue()))
			.count();
	}

	public void update(MilestoneEditRequestDto dto) {
		this.title = dto.getTitle();
		this.description = dto.getDescription();
		this.deadLine = dto.getDeadLine();
		this.status = Status.valueOf(dto.getStatus().toUpperCase());
	}
}
