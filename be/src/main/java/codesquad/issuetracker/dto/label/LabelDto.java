package codesquad.issuetracker.dto.label;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class LabelDto {

    private Long id;
    private String name;
    private String description;
    private String color;
    private boolean isDarkText;

    @QueryProjection
    public LabelDto(Long id, String name, String description, String color, boolean isDarkText) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.isDarkText = isDarkText;
    }
}
