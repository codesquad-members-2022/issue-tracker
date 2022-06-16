package com.team03.issuetracker.issue.repository;

import com.team03.issuetracker.common.config.DataJpaConfig;
import com.team03.issuetracker.issue.domain.Label;
import com.team03.issuetracker.issue.domain.dto.LabelUpdateRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@Import(DataJpaConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class LabelRepositoryTest {

	final LabelRepository labelRepository;

	final EntityManager entityManager;

	@Autowired
	LabelRepositoryTest(LabelRepository labelRepository, EntityManager entityManager) {
		this.labelRepository = labelRepository;
		this.entityManager = entityManager;

		//		List<Label> registeredLabels = List.of(
		//			Label.of(1L, )
		//		)
	}

	@Test
	void 라벨을_생성한다() {
		// given
		Label label = Label.of(null, "제목", "설명", "#000000");

		// when
		Long id = labelRepository.save(label).getId();

		// then
		Label foundLabel = labelRepository.findById(id).get();
		assertThat(foundLabel).usingRecursiveComparison().isEqualTo(label);

	}

	@Test
	void 라벨_목록을_조회한다() {
		// given
		Label label1 = Label.of(null, "제목1", "설명1", "#111111");
		Label label2 = Label.of(null, "제목2", "설명2", "#222222");
		Label label3 = Label.of(null, "제목3", "설명3", "#333333");
		labelRepository.save(label1);
		labelRepository.save(label2);
		Long id = labelRepository.save(label3).getId();

		// when
		List<Label> labels = labelRepository.findAll();
		Label foundLabel = labels.get(labels.size() - 1);

		// then
		assertThat(labels.size()).isEqualTo(3);
		assertThat(foundLabel).usingRecursiveComparison().isEqualTo(label3);
	}

	@Test
	void 라벨_하나를_조회한다() {
		// given
		Label label1 = Label.of(null, "제목1", "설명1", "#111111");
		Long id = labelRepository.save(label1).getId();

		// when
		Label foundLabel = labelRepository.findById(id).get();

		// then
		assertThat(foundLabel).usingRecursiveComparison().isEqualTo(label1);
	}

	@Test
	void 존재하지_않는_라벨을_조회_시_에러가_발생한다() {
		// given
		Label label1 = Label.of(null, "제목1", "설명1", "#111111");
		Long id = labelRepository.save(label1).getId();

		// then
		assertThatThrownBy(() -> labelRepository.findById(id + 1).get()).isInstanceOf(
			NoSuchElementException.class);
	}

	@Test
	void 라벨을_수정한다_모든필드() {
		// given
		Label label = Label.of(null, "제목1", "설명1", "#111111");
		Label savedLabel = labelRepository.save(label);
		LabelUpdateRequest request = new LabelUpdateRequest("수정된 제목1", "수정된 설명1", "#ffffff");

		// when
		savedLabel.update(request);
		Long id = labelRepository.save(savedLabel).getId();

		// then
		Label foundLabel = labelRepository.findById(id).get();
		assertThat(foundLabel).usingRecursiveComparison().isEqualTo(label);
	}

	@Test
	void 라벨을_수정한다_일부필드() {
		// given
		Label label = Label.of(null, "제목1", "설명1", "#111111");
		labelRepository.save(label);
		LabelUpdateRequest request = new LabelUpdateRequest(null, "수정된 설명1", null);

		// when
		label.update(request);
		Long id = labelRepository.save(label).getId();

		// then
		Label foundLabel = labelRepository.findById(id).get();
		assertThat(foundLabel).usingRecursiveComparison().isEqualTo(label);

	}

	@Test
	void 라벨을_일괄적으로_삭제한다() {
		// given
		Label label1 = Label.of(null, "제목1", "설명1", "#111111");
		Label label2 = Label.of(null, "제목2", "설명2", "#222222");
		Label label3 = Label.of(null, "제목3", "설명3", "#333333");

		Long id1 = labelRepository.save(label1).getId();
		Long id2 = labelRepository.save(label2).getId();
		Long id3 = labelRepository.save(label3).getId();

		List<Long> labelIds = new ArrayList<>(List.of(id1, id3));

		// when
		labelRepository.deleteAllByIdInBatch(labelIds);
		entityManager.flush();
		entityManager.clear();

		// then
		List<Label> labels = labelRepository.findAll();
		Label foundLabel = labels.get(0);
		assertThat(labels).hasSize(1);
		assertThat(foundLabel).usingRecursiveComparison().isEqualTo(label2);
		assertThat(labelRepository.findById(id1)).isEmpty();
		assertThat(labelRepository.findById(id3)).isEmpty();
	}

	// Todo : 라벨 삭제시 해당 라벨이 등록되어 있던 Issue에서도 라벨이 삭제되어야한다
	@Test
	void 라벨을_삭제하면_관련된_이슈에서도_라벨이_삭제된다() {
		assertFalse(true);
	}
}
