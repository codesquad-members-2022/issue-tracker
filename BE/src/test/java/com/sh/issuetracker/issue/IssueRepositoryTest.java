package com.sh.issuetracker.issue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class IssueRepositoryTest {
	private final IssueRepository issueRepository;

	public IssueRepositoryTest(
		@Autowired IssueRepository issueRepository) {
		this.issueRepository = issueRepository;
	}

	@Test
	@DisplayName("이슈 조회 요청은 열린 상태의 이슈 목록을 확인한다.")
	void issues_findAll_opened() {
		List<Issue> actual = issueRepository.findAllByProjectIdAndStatus(1L, IssueStatus.OPEN);

		assertThat(actual).extracting("status").contains(IssueStatus.OPEN.toString());
		assertThat(actual).extracting("status").doesNotContain(IssueStatus.CLOSE.toString());
	}

	@Test
	@DisplayName("작성자 조회 요청은 열린 상태의 사용자가 작성한 이슈 목록을 확인한다.")
	void issues_findAll_openedAndUser() {
		long testUserId = 1L;
		List<Issue> actual = issueRepository.findAllByProjectIdAndAuthorIdAndStatus(1L, testUserId,
			IssueStatus.OPEN);

		assertThat(actual).extracting("status").doesNotContain(IssueStatus.CLOSE.toString());
		assertThat(actual).extracting("author.id").contains(testUserId);
	}
}
