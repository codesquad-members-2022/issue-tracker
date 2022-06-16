package codesquad.issuetracker.service;

import codesquad.issuetracker.dto.label.LabelDtoList;
import codesquad.issuetracker.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;

    public LabelDtoList getLabels() {
        return new LabelDtoList(labelRepository.findAll());
    }
}
