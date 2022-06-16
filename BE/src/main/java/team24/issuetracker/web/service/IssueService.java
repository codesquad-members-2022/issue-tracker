package team24.issuetracker.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team24.issuetracker.web.dto.IssueListResponsse;
import team24.issuetracker.web.repository.IssueRepository;

@Service
@RequiredArgsConstructor
public class IssueService {

	private final IssueRepository issueRepository;

	public List<IssueListResponsse> findIssues() {
		return issueRepository.findAll().stream()
			.map(IssueListResponsse::new)
			.collect(Collectors.toList());
	}
}
