package com.team09.issue_tracker.issue;

import com.team09.issue_tracker.common.CommonResponseDto;
import com.team09.issue_tracker.issue.dto.IssueCreateAndUpdateRequestDto;
import com.team09.issue_tracker.issue.dto.IssueListResponseDto;
import com.team09.issue_tracker.issue.dto.IssueFindByIdResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/issues")
public class IssueController {

	private final IssueService issueService;
	private final static Long MEMBER_ID = 1L;

	@GetMapping
	public ResponseEntity<List<IssueListResponseDto>> selectOpened() {
		//TODO : 상수로 사용한 MEMBER_ID는 로그인 구현 완료시 http request 에서 가져오는 것으로 변경
		List<IssueListResponseDto> response = issueService.selectOpened(MEMBER_ID);

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<IssueFindByIdResponseDto> findById(@PathVariable final Long id) {
		return ResponseEntity.ok(new IssueFindByIdResponseDto());
	}

	@PostMapping
	public ResponseEntity<CommonResponseDto> create(
		@RequestBody IssueCreateAndUpdateRequestDto issueCreateRequestDto) {
		return ResponseEntity.ok(new CommonResponseDto());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDto> delete(@PathVariable final Long id) {
		return ResponseEntity.ok(new CommonResponseDto());
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CommonResponseDto> updateState(@PathVariable final Long id, @RequestParam final Boolean setClose) {
		return ResponseEntity.ok(new CommonResponseDto());
	}

	@GetMapping(";type=title")
	public ResponseEntity<List<IssueListResponseDto>> findByTitle(
		@RequestParam final String title) {
		return ResponseEntity.ok().build();
	}

	@GetMapping(";type=filter")
	public ResponseEntity<List<IssueListResponseDto>> findBySearchCondition(
		@ModelAttribute final String issueSearchDto) {
		return ResponseEntity.ok().build();
	}

	@PatchMapping
	public ResponseEntity<CommonResponseDto> updateAllState(@RequestParam final Boolean close,
		@RequestBody final IssueCreateAndUpdateRequestDto issueUpdateAllRequestDto) {
		return ResponseEntity.ok(new CommonResponseDto());
	}
}
