package louie.hanse.issuetracker.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import louie.hanse.issuetracker.domain.Label;
import louie.hanse.issuetracker.repository.LabelRepository;
import louie.hanse.issuetracker.web.dto.label.LabelEditRequest;
import louie.hanse.issuetracker.web.dto.label.LabelListResponse;
import louie.hanse.issuetracker.web.dto.label.LabelSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LabelService {

    private final LabelRepository labelRepository;

    public LabelListResponse getLabelList() {
        List<Label> labels = labelRepository.findAll();
        return new LabelListResponse(labels);
    }

    @Transactional
    public void register(LabelSaveRequest request) {
        Label label = request.toEntity();
        labelRepository.save(label);
    }

    @Transactional
    public void edit(Long id, LabelEditRequest request) {
        Label label = labelRepository.findById(id)
            .orElseThrow(IllegalStateException::new);

        label.changeName(request.getName());
        label.changeDescription(request.getDescription());
        label.changeBackgroundColor(request.getBackgroundColor());
        label.changeTextColor(request.getTextColor());
    }

    @Transactional
    public void delete(Long id) {
        Label label = labelRepository.findById(id)
            .orElseThrow(IllegalStateException::new);
        labelRepository.delete(label);
    }
}
