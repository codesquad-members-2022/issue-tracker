package dev.kukim.issues.milestone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.kukim.issues.MysqlTestContainer;
import dev.kukim.issues.common.exception.InvalidSearchRequestParamException;
import dev.kukim.issues.milestone.controller.request.MilestoneCreateRequest;
import dev.kukim.issues.milestone.controller.request.MilestoneUpdateRequest;
import dev.kukim.issues.milestone.controller.response.MilestoneResponse;
import dev.kukim.issues.milestone.controller.response.MilestoneListResponse;
import dev.kukim.issues.milestone.domain.Milestone;
import dev.kukim.issues.milestone.domain.repository.MileStoneRepository;
import dev.kukim.issues.milestone.exception.MilestoneNotFoundException;
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
	MilestoneCreateRequest milestoneCreateRequest;
	MilestoneUpdateRequest milestoneUpdateRequest;

	@BeforeEach
	void initObject() {
		// 테스트 마일스톤 객체 준비
		this.milestone = Milestone.builder()
			.title("마일스톤1")
			.description("description1")
			.dueDate(LocalDate.now())
			.isOpen(true)
			.build();
		this.milestoneCreateRequest = MilestoneCreateRequest.of("마일스톤 요청1", "description1",
			LocalDate.now());

		this.milestoneUpdateRequest = MilestoneUpdateRequest.of("마일스톤 제목변경", null, null, null);
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
		assertThat(milestoneListResponse.getMilestones()).extracting("title")
			.contains("테스트 마일스톤 1");
	}


	@Test
	void 만약_close_마일스톤검색라면_닫힌마일스톤_전체를반환한다() {
		milestone.setTitle("테스트 마일스톤 1");
		milestone.setDueDate(LocalDate.of(2030, 1, 1));
		milestone.setOpen(true);
		mileStoneRepository.save(milestone);

		MilestoneListResponse milestoneListResponse = milestoneService.findAllBy("close");

		assertThat(milestoneListResponse.getMilestones()).isEmpty();
	}

	@Test
	void 만약_잘못된_status_조건이들어온다면_예외를_발생시킨다() {
		assertThatThrownBy(() -> milestoneService.findAllBy("ErrorStatus"))
			.isInstanceOf(InvalidSearchRequestParamException.class);
	}

	@Test
	void 만약_마일스톤생성_요청이들어온다면_마일스톤을_생성하고_저장한_마일스톤을_리턴한다() {
		milestoneCreateRequest.setTitle("마일스톤 저장 요청1");
		milestoneCreateRequest.setDescription("내용 1");

		MilestoneResponse mileStoneResponse = milestoneService.save(milestoneCreateRequest);

		assertThat(mileStoneResponse.getTitle()).isEqualTo("마일스톤 저장 요청1");
	}


	@Test
	void 만약_특정_마일스톤_제목_수정_요청이들어온다면_마일스톤을_수정하고_수정한_마일스톤을_리턴한다() {
		milestoneCreateRequest.setTitle("마일스톤 1");
		MilestoneResponse saveMilestone = milestoneService.save(milestoneCreateRequest);
		milestoneUpdateRequest.setTitle("마일스톤 제목수정 1");

		MilestoneResponse updateMilestone = milestoneService.update(saveMilestone.getId(),
			milestoneUpdateRequest);

		assertThat(updateMilestone.getId()).isEqualTo(saveMilestone.getId());
		assertThat(updateMilestone.getTitle()).isEqualTo("마일스톤 제목수정 1");
	}

	@Test
	void 만약_특정_마일스톤_많은내용_수정_요청이들어온다면_마일스톤을_수정하고_수정한_마일스톤을_리턴한다() {
		milestoneCreateRequest.setTitle("마일스톤 1");
		MilestoneResponse saveMilestone = milestoneService.save(milestoneCreateRequest);
		milestoneUpdateRequest.setTitle("마일스톤 제목수정 2");
		milestoneUpdateRequest.setDescription("내용 수정");
		milestoneUpdateRequest.setOpen(false);

		MilestoneResponse updateMilestone = milestoneService.update(saveMilestone.getId(),
			milestoneUpdateRequest);

		assertThat(updateMilestone.getId()).isEqualTo(saveMilestone.getId());
		assertThat(updateMilestone.getTitle()).isEqualTo("마일스톤 제목수정 2");
		assertThat(updateMilestone.getDescription()).isEqualTo("내용 수정");
		assertThat(updateMilestone.isOpen()).isFalse();
	}

	@Test
	void 만약_존재하지않는_마일스톤_요청이들어온다면_예외를발생한다() {
		milestoneUpdateRequest.setTitle("마일스톤 제목수정 1");
		assertThatThrownBy(() -> milestoneService.update(100000L,
			milestoneUpdateRequest))
			.isInstanceOf(MilestoneNotFoundException.class);
	}

	@Test
	void 만약_특정_마일스톤_삭제요청이_주어진다면_해당마일스톤을삭제한다() {
		milestoneCreateRequest.setTitle("마일스톤 저장 1");
		MilestoneResponse saveMilestone = milestoneService.save(milestoneCreateRequest);

		milestoneService.delete(saveMilestone.getId());

		assertThat(mileStoneRepository.findById(saveMilestone.getId())).isEmpty();
	}

	@Test
	void 만약_존재하지않는_마일스톤_삭제요청이_온다면_예외를발생한다() {
		assertThatThrownBy(() -> milestoneService.delete(1000000L))
			.isInstanceOf(MilestoneNotFoundException.class);
	}
}
