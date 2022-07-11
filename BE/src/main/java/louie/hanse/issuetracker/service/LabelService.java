package louie.hanse.issuetracker.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Label;
import louie.hanse.issuetracker.repository.LabelRepository;
import louie.hanse.issuetracker.web.dto.label.LabelListResponse;
import louie.hanse.issuetracker.web.dto.label.LabelSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LabelService {

    private final LabelRepository labelRepository;

    @Transactional
    public void register(LabelSaveRequest request) {
        Label label = request.toEntity();
        labelRepository.save(label);
    }

    public LabelListResponse getLabelList() {
        List<Label> labels = labelRepository.findAll();
        return new LabelListResponse(labels);
    }
}
