package com.team09.issue_tracker.issue.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IssueUpdateAllRequestDto {

	private List<Long> id;

}
