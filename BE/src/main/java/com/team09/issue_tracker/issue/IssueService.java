package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.issue.dto.IssueListResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class IssueService {

	private final IssueRepository issueRepository;

	@Transactional(readOnly = true)
	public List<IssueListResponseDto> selectOpened(Long memberId) {
		List<Issue> issues = issueRepository.findByWriterIdAndIsOpened(memberId,
			true);

		List<IssueListResponseDto> response = issues.stream().map(Issue::toResponseDto)
			.collect(Collectors.toList());

		return response;
	}
}
