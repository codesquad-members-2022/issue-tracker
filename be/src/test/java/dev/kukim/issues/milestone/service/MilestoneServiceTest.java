package dev.kukim.issues.milestone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.kukim.issues.MysqlTestContainer;
import dev.kukim.issues.common.exception.InvalidSearchRequestParamException;
import dev.kukim.issues.milestone.controller.response.MilestoneListResponse;
import dev.kukim.issues.milestone.domain.Milestone;
import dev.kukim.issues.milestone.domain.repository.MileStoneRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("MilestoneServiceTest 클래스")
@SpringBootTest
class MilestoneServiceTest extends MysqlTestContainer {

	@Autowired
	MilestoneService milestoneService;

	@Autowired
	MileStoneRepository mileStoneRepository;

	Milestone milestone;

	@BeforeEach
	void initObject() {
		// 테스트 마일스톤 객체 준비
		this.milestone = Milestone.of("마일스톤1", "description1", LocalDate.now(), true);
	}

	@BeforeEach
	void cleanUpDB() {
		// DB cleanup
		mileStoneRepository.deleteAll();
	}

	@Test
	void 만약_open_마일스톤검색이라면_오픈된마일스톤_전체를반환한다() {
		milestone.setTitle("테스트 마일스톤 1");
		milestone.setDueDate(LocalDate.of(2030, 1, 1));
		milestone.setOpen(true);
		mileStoneRepository.save(milestone);

		MilestoneListResponse milestoneListResponse = milestoneService.findAllBy("open");

		assertThat(milestoneListResponse.getMilestones()).hasSize(1);
		assertThat(milestoneListResponse.getMilestones()).extracting("title").contains("테스트 마일스톤 1");
	}


	@Test
	void 만약_close_마일스톤검색라면_닫힌마일스톤_전체를반환한다() {
		milestone.setTitle("테스트 마일스톤 1");
		milestone.setDueDate(LocalDate.of(2030, 1, 1));
		milestone.setOpen(true);
		mileStoneRepository.save(milestone);

		MilestoneListResponse milestoneListResponse = milestoneService.findAllBy("close");

		assertThat(milestoneListResponse.getMilestones()).hasSize(0);
	}

	@Test
	void 만약_잘못된_status_조건이들어온다면_예외를_발생시킨다() {
		assertThatThrownBy(() -> milestoneService.findAllBy("ErrorStatus"))
			.isInstanceOf(InvalidSearchRequestParamException.class);
	}


}
