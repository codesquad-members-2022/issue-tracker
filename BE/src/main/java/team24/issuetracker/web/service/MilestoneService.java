package team24.issuetracker.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
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
			long totalIssue = milestone.getIssues().size();
			long openedIssue = milestone.getIssues().stream().filter(issue -> !issue.isClosed()).count();
			milestoneListResponses.add(new MilestoneListResponse(milestone, totalIssue, openedIssue));
		}
		return  milestoneListResponses;
	}
}
