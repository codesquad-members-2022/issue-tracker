package kr.codesquad.issuetracker.web.dto.milestone;

import java.time.LocalDate;
import java.time.LocalDateTime;
import kr.codesquad.issuetracker.domain.milestone.Milestone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneRequestDto {

	private String title;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate deadLine;

	public Milestone toEntity() {
		return new Milestone(
			title,
			description,
			deadLine
		);
	}
}
