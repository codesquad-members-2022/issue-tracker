package codesquad.issuetracker.dto.issue.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IssueCreateForm {

    @NotBlank
    private String subject;
    @JsonFormat(shape = Shape.NUMBER_INT)
    @NotNull
    private Long writerId;
    @JsonFormat(shape = Shape.ARRAY)
    @NotNull
    private List<Long> assigneeIds;
    @JsonFormat(shape = Shape.ARRAY)
    @NotNull
    private List<Long> labelIds;
    @JsonFormat(shape = Shape.NUMBER_INT)
    private Long milestoneId;
    @NotBlank
    private String comment;
}
