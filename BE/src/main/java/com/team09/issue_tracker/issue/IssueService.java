package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.common.CommonResponseDto;
import com.team09.issue_tracker.issue.domain.Issue;
import com.team09.issue_tracker.issue.domain.IssueAssignee;
import com.team09.issue_tracker.issue.dto.IssueDetailResponseDto;
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
	private final IssueAssigneeRepository issueAssigneeRepository;

	@Transactional(readOnly = true)
	public List<IssueListResponseDto> selectOpenedList(Long memberId) {
		List<Issue> issues = issueRepository.findByMemberIdAndIsOpened(memberId,
			true);

		List<IssueListResponseDto> response = issues.stream().map(Issue::toListResponse)
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

	@Transactional(readOnly = true)
	public IssueDetailResponseDto selectOneById(Long issueId, Long memberId) {
		// TODO : id가 작성/할당 된 이슈만 "더보기" 버튼 생성 하여 상세정보 수정/삭제 가능
		boolean isEditable = false;

		//1. 조회하면서 작성자인지 확인
		Issue issue = issueRepository.findById(issueId)
			.orElseThrow(() -> new RuntimeException("")); // TODO: 커스텀 익셉션 생성

		if (issue.isWriter(memberId)) {
			isEditable = true;
		}

		//2. 할당자인지 확인
		List<IssueAssignee> issueAssignees = issueAssigneeRepository.findByIssueId(issue.getId());
		if (issueAssignees.size() != 0) {
			isEditable = true;
		}

		return issue.toDetailResponse(isEditable);
	}

}
