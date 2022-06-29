package kr.codesquad.issuetracker.service;

import static kr.codesquad.issuetracker.exception.ErrorMessage.*;

import java.util.List;
import java.util.stream.Collectors;
import kr.codesquad.issuetracker.domain.label.Label;
import kr.codesquad.issuetracker.domain.label.repository.LabelRepository;
import kr.codesquad.issuetracker.domain.milestone.repository.MilestoneRepository;
import kr.codesquad.issuetracker.exception.CustomException;
import kr.codesquad.issuetracker.web.dto.label.LabelDto;
import kr.codesquad.issuetracker.web.dto.label.LabelRequestDto;
import kr.codesquad.issuetracker.web.dto.label.LabelResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LabelService {

	private final LabelRepository labelRepository;
	private final MilestoneRepository milestoneRepository;

	@Transactional(readOnly = true)
	public LabelResponseDto list() {
		return new LabelResponseDto(
			(int) labelRepository.count(),
			(int) milestoneRepository.count(),
			findLabelDtos()
		);
	}

	private List<LabelDto> findLabelDtos() {
		return labelRepository.findAll().stream()
			.map(LabelDto::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public void add(LabelRequestDto dto) {
		labelRepository.save(dto.toEntity());
	}

	@Transactional
	public void delete(Long id) {
		Label label = labelRepository.findById(id).orElseThrow(() -> new CustomException(LABEL_NOT_FOUND));
		labelRepository.delete(label);
	}

	@Transactional
	public void update(Long id, LabelRequestDto dto) {
		Label label = labelRepository.findById(id).orElseThrow(
			() -> new CustomException(LABEL_NOT_FOUND)
		);
		label.update(dto);
	}

	public List<Label> findLabels(List<Long> labels) {
		return labelRepository.findAllById(labels);
	}
}
