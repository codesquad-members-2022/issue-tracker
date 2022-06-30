package team29.hoorry.issuetracker.core.issue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team29.hoorry.issuetracker.core.issue.dto.request.IssueAssigneesUpdateRequest;
import team29.hoorry.issuetracker.core.issue.dto.request.IssueLabelsUpdateRequest;
import team29.hoorry.issuetracker.core.issue.dto.request.IssueMilestoneUpdateRequest;
import team29.hoorry.issuetracker.core.issue.dto.request.IssueTitleUpdateRequest;

@Slf4j
@Service
public class IssueMockService {

	public Long updateTitle(Long id, IssueTitleUpdateRequest issueTitleUpdateRequest) {
		log.debug("status id = {}", id);
		log.debug("title = {}", issueTitleUpdateRequest.getTitle());
		return id;
	}

	public Long updateLabels(Long id, IssueLabelsUpdateRequest issueLabelsUpdateRequest) {
		issueLabelsUpdateRequest.getLabelsId()
			.forEach(labelId -> log.debug("label id = {}", labelId));
		return id;
	}

	public Long updateMilestone(Long id, IssueMilestoneUpdateRequest issueMilestoneUpdateRequest) {
		log.debug("milestone id = {}", issueMilestoneUpdateRequest.getMilestoneId());
		return id;
	}

	public Long updateAssignees(Long id, IssueAssigneesUpdateRequest issueAssigneesUpdateRequest) {
		issueAssigneesUpdateRequest.getAssigneesId()
			.forEach(assigneeId -> log.debug("assignee id = {}", assigneeId));
		return id;
	}
}
