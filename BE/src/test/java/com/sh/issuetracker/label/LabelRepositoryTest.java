package com.sh.issuetracker.label;

import static com.sh.issuetracker.milestone.MilestoneRepositoryTest.TEXT_PROJECT_ID;
import static org.assertj.core.api.Assertions.assertThat;

import com.sh.issuetracker.project.Project;
import com.sh.issuetracker.project.ProjectRepository;

import org.junit.jupiter.api.BeforeEach;
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
class LabelRepositoryTest {
	public static final String TEST_FOR_LABEL_NAME = "테스트1**";
	private final ProjectRepository projectRepository;
	private final LabelRepository labelRepository;

	private Project project;

	public LabelRepositoryTest(
		@Autowired ProjectRepository projectRepository,
		@Autowired LabelRepository labelRepository) {
		this.projectRepository = projectRepository;
		this.labelRepository = labelRepository;
	}

	@BeforeEach
	void beforeEach() {
		project = projectRepository.findById(TEXT_PROJECT_ID)
			.orElseThrow(() -> new RuntimeException("해당 프로젝트는 존재하지 않습니다."));
		labelRepository.save(getLabel(TEST_FOR_LABEL_NAME, "#28E7FF"));
	}

	@Test
	@DisplayName("요청받은 라벨정보는 DB에 저장됨을 삭제 대상이 아닌 조회결과로 확인한다.")
	void save_findAll_isNotDeleted() {
		List<Label> actual = labelRepository.findAllByProjectId(TEXT_PROJECT_ID);

		assertThat(actual).extracting("name").contains(TEST_FOR_LABEL_NAME);
		assertThat(actual).extracting("isDeleted").contains(false);
	}

	@Test
	@DisplayName("삭제요청 받은 라벨정보는 업데이트 결과를 조회결과로 확인한다.")
	void update_findAll_isDeleted() {
		String expected = "삭제처리될 라벨 테스트";
		Label label = labelRepository.save(getLabel(expected, "#6ED746"));
		label.delete();

		List<Label> actual = labelRepository.findAllByProjectId(TEXT_PROJECT_ID);

		assertThat(actual).extracting("name").doesNotContain(expected);
		assertThat(actual).extracting("isDeleted").contains(false);
	}

	private Label getLabel(String name, String backgroundColor) {
		return new Label(name, "테스트-라벨설명", backgroundColor, TextColor.DARK, project);
	}
}
