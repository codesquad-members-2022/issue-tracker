package com.team33.backend.issue.service;

import com.team33.backend.issue.controller.dto.label.LabelEditRequest;
import com.team33.backend.issue.domain.Label;
import com.team33.backend.issue.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;
    private final LabelQueryRepository labelQueryRepository;

    private List<Label> getLabelCount(Long issueId){
        return labelQueryRepository.getLablesByIssueId(issueId);
    }

    @Transactional
    public Label editLabel(Long labelId, LabelEditRequest request) {
        Label findLabel = labelRepository.findById(labelId).orElseThrow();
        findLabel.editLabel(request.getName(), request.getDescription(), request.getColor());
        return findLabel;
    }

    @Transactional
    public Long deleteLabelById(Long labelId) {
        Label findLabel = labelRepository.findById(labelId).orElseThrow();
        labelRepository.delete(findLabel);
        return labelId;
    }
}
