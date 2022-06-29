package com.team09.issue_tracker.label;

import com.team09.issue_tracker.exception.LabelInvalidException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LabelService {

	private final LabelRepository labelRepository;

	public void  validateLabelIds(List<Long> labelIds, Long memberId) {
		if (labelIds.size() > 0) {
			validateMyLabelIds(labelIds, memberId);
		}
	}

	private void validateMyLabelIds(List<Long> labelIds, Long memberId) {
		if (!isMyLabels(labelIds, memberId)) {
			throw new LabelInvalidException();
		}
	}

	private boolean isMyLabels(List<Long> labelIds, Long memberId) {
		long countOfLabelFromDb = labelRepository.countByIdInAndMemberId(labelIds, memberId);
		return countOfLabelFromDb == labelIds.size();
	}
}
