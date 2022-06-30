package team29.hoorry.issuetracker.core.common.fileupload;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team29.hoorry.issuetracker.core.exception.dto.ErrorResponse;

@Tag(name = "common", description = "공통 API")
@RestController
@RequiredArgsConstructor
public class FileUploadController {

	private final FileUploadService fileUploadService;

	@Operation(
		summary = "파일 업로드 요청",
		description = "파일을 업로드합니다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "파일 업로드 성공",
				content = {
					@Content(
						mediaType = "application/json",
						schema = @Schema(implementation = FileUploadResponse.class)
					)
				}),
			@ApiResponse(
				responseCode = "400",
				description = "파일 업로드 실패",
				content = {
					@Content(
						mediaType = "application/json",
						schema = @Schema(implementation = ErrorResponse.class)
					)
				}
			)
		})
	@PostMapping("/api/upload")
	public ResponseEntity<FileUploadResponse> uploadFile(@RequestPart MultipartFile file) {
		return ResponseEntity.ok(fileUploadService.uploadFile(file));
	}
}
