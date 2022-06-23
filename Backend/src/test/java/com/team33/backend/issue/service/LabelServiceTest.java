package com.team33.backend.issue.service;

import com.team33.backend.TestConfig;
import com.team33.backend.data.LabelData;
import com.team33.backend.issue.controller.dto.label.LabelEditRequest;
import com.team33.backend.issue.domain.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase
@Import({TestConfig.class, LabelService.class, LabelQueryRepository.class})
@DisplayName("LabelService 테스트")
class LabelServiceTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    LabelService labelService;

    @BeforeEach
    void setUp() {
        LabelData data = new LabelData();
        List<Label> labels = data.getLabels();
        labels.forEach(entity -> testEntityManager.persist(entity));
        testEntityManager.flush();
        testEntityManager.clear();
    }

//    @Test
//    @DisplayName("라벨을 삭제하는 경우 해당 프로젝트에서 라벨이 더 이상 조회되지 않는다.")
//    void 라벨_삭제() {
//
//        // when
//        labelService.deleteLabelById(1L);
//        testEntityManager.flush();
//        testEntityManager.clear();
//
//        assertThrows(NullPointerException.class, ()->testEntityManager.find(Label.class, 1L));
//    }

    @Test
    @DisplayName("라벨을 수정하는 경우 해당 라벨의 속성이 변경된다.")
    void 라벨_수정() {

        LabelEditRequest request = new LabelEditRequest("BugFix", "버그 수정", "A3431A");
        // when
        Label findLabel = labelService.editLabel(1L, request);
        findLabel.editLabel(request.getName(), request.getDescription(), request.getColor());
        testEntityManager.flush();
        testEntityManager.clear();

        // then
        Label findLabelAfterEdit = testEntityManager.find(Label.class, 1L);
        assertThat(findLabelAfterEdit.getName()).isEqualTo("BugFix");
        assertThat(findLabelAfterEdit.getDescription()).isEqualTo("버그 수정");
        assertThat(findLabelAfterEdit.getColor()).isEqualTo("A3431A");
    }
}
