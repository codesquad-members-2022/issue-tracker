package team24.issuetracker.web.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import team24.issuetracker.domain.Assignee;

@Getter
public class Assignees {

	private final List<Assignee> assignees;

	public Assignees(List<Assignee> assignees) {
		this.assignees = new ArrayList<>(assignees);
	}
}
