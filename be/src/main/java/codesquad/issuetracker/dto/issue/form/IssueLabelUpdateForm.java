package codesquad.issuetracker.dto.issue.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IssueLabelUpdateForm {

    @JsonFormat(shape = Shape.ARRAY)
    @NotNull
    private List<Long> labels;

}
