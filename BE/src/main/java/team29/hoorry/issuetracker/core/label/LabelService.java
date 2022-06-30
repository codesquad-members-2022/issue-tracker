package team29.hoorry.issuetracker.core.label;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team29.hoorry.issuetracker.core.label.domain.Label;
import team29.hoorry.issuetracker.core.label.dto.LabelAddRequest;
import team29.hoorry.issuetracker.core.label.dto.LabelResponse;
import team29.hoorry.issuetracker.core.label.dto.LabelUpdateRequest;
import team29.hoorry.issuetracker.core.label.dto.LabelsResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class LabelService {

	private final LabelRepository labelRepository;

	public LabelsResponse findAll() {
		List<LabelResponse> labelResponses = labelRepository.findAll().stream()
			.map(LabelResponse::from)
			.collect(Collectors.toList());

		return new LabelsResponse(labelResponses);
	}

	public void save(LabelAddRequest labelAddRequest) {
		Label newLabel = labelAddRequest.toEntity();
		labelRepository.save(newLabel);
	}

	public void update(Long id, LabelUpdateRequest labelUpdateRequest) {
		Label label = labelRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("존재하지 않는 labelId입니다."));
		label.update(
			labelUpdateRequest.getTitle(),
			labelUpdateRequest.getTitleColor(),
			labelUpdateRequest.getBackgroundColor(),
			labelUpdateRequest.getDescription()
		);
	}

	public void delete(Long id) {
		Label label = labelRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("존재하지 않는 labelId입니다."));
		labelRepository.delete(label);
	}
}
