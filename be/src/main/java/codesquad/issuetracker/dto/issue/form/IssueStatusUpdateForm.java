package codesquad.issuetracker.dto.issue.form;

import codesquad.issuetracker.domain.IssueStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IssueStatusUpdateForm {

    @NotNull
    private IssueStatus updatedStatus;
    @JsonFormat(shape = Shape.ARRAY)
    @NotNull
    private List<Long> idOfIssues;

}
