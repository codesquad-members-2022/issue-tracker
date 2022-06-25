package kr.codesquad.issuetracker.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ErrorResponse {

	private String error;
	private String message;
	private String code;
	@JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss")
	private LocalDateTime createTime;

	public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorMessage message) {
		return ResponseEntity
			.status(message.getHttpStatus())
			.body(new ErrorResponse(
				message.getHttpStatus().name(),
				message.getMessage(),
				message.name(),
				LocalDateTime.now()
			));
	}
}
