package com.team09.issue_tracker.label;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LabelService {

	private final LabelRepository labelRepository;

	public boolean isMyLabels(List<Long> labelIds, Long memberId) {
		long countOfLabelFromDb = labelRepository.countByIdInAndMemberId(labelIds, memberId);

		if(countOfLabelFromDb == labelIds.size()){
			return true;
		}
		return false;
	}
}
