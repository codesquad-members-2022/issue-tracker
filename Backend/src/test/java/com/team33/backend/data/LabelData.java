package com.team33.backend.data;

import com.team33.backend.issue.domain.Label;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LabelData {

    private Label label;
    private List<Label> labels;

    public List<Label> getLabels() {
        if (this.label == null || labels.size() == 0) {
            this.labels = new ArrayList<>();
            this.labels.add(createBugLabel());
            this.labels.add(createFixLabel());
            this.labels.add(createFeatureLabel());
            this.labels.add(createBuildLabel());
        }
        return labels;
    }

    public Label getLabelById(String kind) {
        if (kind == null) {
            throw new IllegalArgumentException("라벨 종류를 입력해주세요.");
        }

        if (this.label == null || labels.size() == 0) {
            this.label = getLabel();
        }
        return labels.stream()
                .filter(label -> label.getName().equals(kind))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 라벨을 찾을 수 없습니다."));
    }

    private Label createBugLabel() {
        return new Label("Bug", "오류와 관련된 라벨입니다.", "#A04B9");
    }

    private Label createFixLabel() {
        return new Label("Fix", "Fix 관련된 라벨입니다.", "#C04B9");
    }

    private Label createFeatureLabel() {
        return new Label("Feature", "기능 개발과 관련된 라벨입니다.", "#B04B9");
    }

    private Label createBuildLabel() {
        return new Label("Build", "프로젝트 빌드와 관련된 라벨입니다.", "#D04B9");
    }
}
