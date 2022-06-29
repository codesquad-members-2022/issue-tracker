package kr.codesquad.issuetracker.web.dto.milestone;

import java.time.LocalDate;
import kr.codesquad.issuetracker.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneEditRequestDto {

	private String title;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate deadLine;

	private Status status;
}
