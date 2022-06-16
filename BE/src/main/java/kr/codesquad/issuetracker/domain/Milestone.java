package kr.codesquad.issuetracker.domain;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Milestone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "milestone_id")
	private Long id;

	private String title;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime deadLine;

	@OneToMany(mappedBy = "milestone")
	private List<Issue> issueList = new ArrayList<>();
}
