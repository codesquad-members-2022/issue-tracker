package team24.issuetracker.web.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team24.issuetracker.web.dto.labellist.LabelListResponse;
import team24.issuetracker.web.repository.LabelRepository;

@Service
@RequiredArgsConstructor
public class LabelService {

	private final LabelRepository labelRepository;

	public List<LabelListResponse> findLabel() {
		return labelRepository.findAll().stream().map(LabelListResponse::new).collect(
			Collectors.toList());
	}
}
