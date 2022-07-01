package team25.issuetracker.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ResponseTokenMessage {

	private String accessToken;
	private String refreshToken;
	private HttpStatus errorType;
	private HttpStatus status;
	private String message;

	@Builder
	public ResponseTokenMessage(String accessToken, String refreshToken,
		HttpStatus errorType, HttpStatus status, String message) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.errorType = errorType;
		this.status = status;
		this.message = message;
	}
}
