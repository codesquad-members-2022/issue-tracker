package com.team09.issue_tracker.issue;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	@EntityGraph(attributePaths = {"milestone", "issueLabels", "issueLabels.label"})
	@Query("select i from Issue i "
		+ "where i.memberId = :writerId "
		+ "and i.isOpened = :isOpened")
	List<Issue> findByWriterIdAndIsOpened(@Param("writerId") Long writerId,
		@Param("isOpened") boolean isOpened);

}
