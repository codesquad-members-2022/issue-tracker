package com.team09.issue_tracker.issue;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DisplayName("IssueRepository 메서드")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class IssueRepositoryTest {

	@Autowired
	IssueRepository issueRepository;

	@Test
	void findByWriterIdAndIsOpened() {
		//given
		Long writerId = 1L;
		boolean isOpened = true;

		//when
		List<Issue> issues = issueRepository.findByWriterIdAndIsOpened(writerId,
			isOpened);

		//then
		assertThat(issues).hasSize(18).anyMatch(issue -> issue != null);
	}

}