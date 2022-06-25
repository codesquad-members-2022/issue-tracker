package com.team09.issue_tracker.label;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

	//하나라도 존재하면 true
	boolean existsByIdInAndMemberId(Set<Long> ids, Long memberId);
	boolean existsByIdAndMemberId(Long id, Long memberId);

	long countByIdInAndMemberId(List<Long> ids, Long memberId);

}
