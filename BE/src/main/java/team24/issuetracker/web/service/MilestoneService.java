package team24.issuetracker.web.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
		milestoneList.stream().map(Milestone::getIssues).collect(Collectors.toList())

	}
}
