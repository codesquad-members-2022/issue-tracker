package com.team09.issue_tracker.issue;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.team09.issue_tracker.issue.dto.IssueSaveRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("IssueRepository 이슈 생성")
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class IssueCreateRepositoryTest {

	@Autowired
	IssueRepository issueRepository;
	final Long MEMBER_ID = 1L;

	@DisplayName("필수 요소만 가진 이슈")
	@Test
	void saveOnlyMandatory() {
		//given
		IssueSaveRequestDto issueSaveRequestDto = IssueSaveRequestDto.builder()
			.title("제목")
			.content("내용")
			.build();
		boolean isOpened = true;
		Issue issue = Issue.fromForMandatory(issueSaveRequestDto, isOpened, MEMBER_ID);

		//when
		Issue savedIssue = issueRepository.save(issue);

		//then
		assertAll(
			() -> assertThat(savedIssue.getTitle()).isEqualTo("제목"),
			() -> assertThat(savedIssue.getContent()).isEqualTo("내용")
		);
	}
}
