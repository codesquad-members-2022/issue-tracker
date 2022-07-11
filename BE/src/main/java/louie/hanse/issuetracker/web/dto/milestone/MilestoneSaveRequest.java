package louie.hanse.issuetracker.web.dto.milestone;

import lombok.Getter;
import louie.hanse.issuetracker.domain.Milestone;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
public class MilestoneSaveRequest {
    @NotEmpty
    private String title;
    private String description;
    private LocalDate completedDate;

    public Milestone toEntity() {
       return new Milestone(title, description, completedDate);
    }
}
