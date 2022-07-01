package team25.issuetracker.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class RefreshJson {

	private String accessToken;
	private HttpStatus errorType;
	private HttpStatus status;
	private String message;

	@Builder
	public RefreshJson(String accessToken, HttpStatus errorType,
		HttpStatus status, String message) {
		this.accessToken = accessToken;
		this.errorType = errorType;
		this.status = status;
		this.message = message;
	}
}
