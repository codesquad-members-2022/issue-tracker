package kr.codesquad.issuetracker.service;

import static kr.codesquad.issuetracker.exception.ErrorMessage.ISSUE_NOT_FOUND;

import java.util.List;
import kr.codesquad.issuetracker.domain.issue.Issue;
import kr.codesquad.issuetracker.domain.issue.repository.IssueRepository;
import kr.codesquad.issuetracker.domain.label.repository.LabelRepository;
import kr.codesquad.issuetracker.domain.milestone.repository.MilestoneRepository;
import kr.codesquad.issuetracker.exception.CustomException;
import kr.codesquad.issuetracker.web.dto.issue.IssueModifyRequestDto;
import kr.codesquad.issuetracker.web.dto.issue.IssueResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class IssueModifyService {

	private final IssueRepository issueRepository;


	@Transactional
	public void statusModify(IssueModifyRequestDto dto) {
		List<Long> ids = dto.getIds();
		for (Long id : ids) {
			Issue issue = issueRepository.findById(id)
				.orElseThrow(() -> new CustomException(ISSUE_NOT_FOUND));

			issue.updateStatus(dto.getStatus());
		}
	}
}
