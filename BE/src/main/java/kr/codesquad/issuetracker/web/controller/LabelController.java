package kr.codesquad.issuetracker.web.controller;

import kr.codesquad.issuetracker.auth.annotation.LoginVerify;
import kr.codesquad.issuetracker.service.LabelService;
import kr.codesquad.issuetracker.web.dto.label.LabelRequestDto;
import kr.codesquad.issuetracker.web.dto.label.LabelResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/labels")
@RestController
public class LabelController {

	private final LabelService labelService;

	@GetMapping
	public LabelResponseDto labelList() {
		log.debug("라벨 조회");
		return labelService.list();
	}

	@LoginVerify
	@PostMapping
	public void labelAdd(@RequestBody LabelRequestDto dto) {
		log.debug("라벨 추가");
		labelService.add(dto);
	}

	@LoginVerify
	@DeleteMapping("{id}")
	public void labelDelete(@PathVariable Long id) {
		log.debug("라벨 삭제");
		labelService.delete(id);
	}

	@LoginVerify
	@PostMapping("{id}")
	public void labelEdit(@PathVariable Long id, @RequestBody LabelRequestDto dto) {
		log.debug("라벨 수정");
		labelService.update(id, dto);
	}
}
