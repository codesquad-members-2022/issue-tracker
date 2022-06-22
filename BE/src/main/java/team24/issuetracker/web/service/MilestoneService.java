package team24.issuetracker.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.domain.Issue;
import team24.issuetracker.domain.Milestone;
import team24.issuetracker.web.dto.milestonelist.MilestoneListResponse;
import team24.issuetracker.web.repository.MilestoneRepository;

@Service
@RequiredArgsConstructor
public class MilestoneService {

	private final MilestoneRepository milestoneRepository;

	public List<MilestoneListResponse> findAll() {
		List<Milestone> milestoneList = milestoneRepository.findAll();
		List<MilestoneListResponse> milestoneListResponses = new ArrayList<>();
		for (Milestone milestone : milestoneList) {
			long totalIssueCount = milestone.getIssues().size();
			long openedIssueCount = countOpenedIssue(milestone);
			milestoneListResponses.add(new MilestoneListResponse(milestone, totalIssueCount, openedIssueCount));
		}
		return milestoneListResponses;
	}

	private Long countOpenedIssue(Milestone milestone) {
		return milestone.getIssues().stream()
			.filter(Predicate.not(Issue::isClosed))
			.count();
	}
}
