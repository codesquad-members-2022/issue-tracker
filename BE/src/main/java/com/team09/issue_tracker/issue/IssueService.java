package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.common.CommonResponseDto;
import com.team09.issue_tracker.issue.dto.IssueSaveRequestDto;
import com.team09.issue_tracker.issue.dto.IssueListResponseDto;
import java.util.Collections;
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

		return Collections.unmodifiableList(response);
	}

	@Transactional
	public CommonResponseDto create(IssueSaveRequestDto issueCreateRequestDto, Long memberId) {
		Long milestoneId = issueCreateRequestDto.getMilestoneId();
		List<Long> labelIds = issueCreateRequestDto.getLabelIds();
		List<Long> assigneeIds = issueCreateRequestDto.getAssigneeIds();
		if (milestoneId != null && labelIds != null && assigneeIds != null) {
			return null;
		}

		boolean isOpened = true;
		Issue issue = issueRepository.save(
			Issue.fromForMandatory(issueCreateRequestDto, isOpened, memberId));
		CommonResponseDto response = issue.toCommonResponse();
		return response;
	}
}
