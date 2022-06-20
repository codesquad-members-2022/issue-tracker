package codesquad.issuetracker.service;

import codesquad.issuetracker.dto.label.LabelDtos;
import codesquad.issuetracker.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LabelService {

    private final LabelRepository labelRepository;

    public LabelDtos getLabels() {
        return new LabelDtos(labelRepository.findAll());
    }
}
