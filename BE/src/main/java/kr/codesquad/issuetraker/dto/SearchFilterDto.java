package kr.codesquad.issuetraker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchFilterDto {
    private boolean isOpened;
    private Long authorId;
    private Long labelId;
    private Long milestoneId;
}
