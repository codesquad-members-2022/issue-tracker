package com.team09.issue_tracker.issue;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("IssueRepository 이슈 조회")
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class IssueSelectRepositoryTest {

	@Autowired
	IssueRepository issueRepository;

	@DisplayName("나의 열린 상태 이슈 목록")
	@Test
	void findByWriterIdAndIsOpened() {
		//given
		Long writerId = 1L;
		boolean isOpened = true;

		//when
		List<Issue> issues = issueRepository.findByWriterIdAndIsOpened(writerId,
			isOpened);

		//then
		assertThat(issues).hasSize(3).anyMatch(issue -> issue != null);
	}
}