package codesquad.issuetracker.dto.label;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class LabelDto {

    private Long id;
    private String name;
    private String color;

    @QueryProjection
    public LabelDto(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
